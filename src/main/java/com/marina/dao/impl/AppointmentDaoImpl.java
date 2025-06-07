package com.marina.dao.impl;

import java.io.IOException;

import com.marina.dao.ConnectionDao;
import com.marina.dao.interfaces.AppointmentDao;
import com.marina.model.Appointment;

public class AppointmentDaoImpl implements AppointmentDao {
    private static final String ENDPOINT = "consulta";

    @Override
    public String createAppointment(Appointment appointment) throws IOException {
        String jsonData = "{"
                + "\"doctorCpf\": \"" + appointment.getDoctor() + "\","
                + "\"patientCpf\": \"" + appointment.getPatient() + "\","
                + "\"date\": \"" + appointment.getDate() + "\""
                + "}";
        return ConnectionDao.makePostRequest(ENDPOINT, jsonData);
    }

    @Override
    public String getAppointment(String id) throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT + "/" + id);
    }

    @Override
    public String updateAppointment(Appointment appointment) throws IOException {
        String jsonData = "{"
                + "\"observation\": \"" + appointment.getObservation() + "\","
                + "\"status\": \"" + appointment.getStatus().getCode() + "\","
                + "\"id\": \"" + appointment.getId() + "\""
                + "}";
        return ConnectionDao.makePutRequest(ENDPOINT + "/" + appointment.getId(), jsonData);
    }

    @Override
    public void deleteAppointment(String id) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + id);
    }

    @Override
    public String listAppointments() throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT);
    }
}
