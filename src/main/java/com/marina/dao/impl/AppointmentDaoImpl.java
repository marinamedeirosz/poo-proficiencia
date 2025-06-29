package com.marina.dao.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.marina.dao.ConnectionDao;
import com.marina.dao.interfaces.AppointmentDao;
import com.marina.model.Appointment;

public class AppointmentDaoImpl implements AppointmentDao {
    private static final String ENDPOINT = "consulta";

    private static final String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    @Override
    public String createAppointment(Appointment appointment) throws IOException {
        LocalDateTime date = appointment.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        String jsonData = "{"
                + "\"doctorCrm\": \"" + appointment.getDoctor() + "\","
                + "\"patientCpf\": \"" + appointment.getPatient() + "\","
                + "\"appointmentDate\": \"" + formatter.format(date) + "\","
                + "\"status\": \"" + appointment.getStatus().getCode() + "\","
                + "\"observation\": \"" + appointment.getObservation() + "\""
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
