package com.marina.dao;

import java.io.IOException;

import com.marina.model.Appointment;

public class AppointmentDao {
    private static final String ENDPOINT = "consulta";

    public static String createAppointment(Appointment appointment) throws IOException {
        String jsonData = "{"
                + "\"doctorCpf\": \"" + appointment.getDoctor() + "\","
                + "\"patientCpf\": \"" + appointment.getPatient() + "\","
                + "\"date\": \"" + appointment.getDate() + "\""
                + "}";
        return ConnectionDao.makePostRequest(ENDPOINT, jsonData);
    }
    
    public static String getAppointment(String id) throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT + "/" + id);
    }
    
    public static String updateAppointment(Appointment appointment) throws IOException {
        String jsonData = "{"
                + "\"observation\": \"" + appointment.getObservation() + "\","
                + "\"status\": \"" + appointment.getStatus().getCode() + "\","
                + "\"id\": \"" + appointment.getId() + "\""
                + "}";
        return ConnectionDao.makePutRequest(ENDPOINT + "/" + appointment.getId(), jsonData);    
    }
    
    public static void deleteAppointment(String id) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + id);
    }

    public static String listAppointments() throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT);
    }
}
