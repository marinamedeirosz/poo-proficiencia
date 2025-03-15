package com.marina.dao;

import java.io.IOException;

import com.marina.model.Appointment;

public class AppointmentDao {
    private static final String ENDPOINT = "consulta";

    public static String createAppointment(Appointment appointment) throws IOException {
        String jsonData = "{"
                + "\"doctorCpf\": \"" + appointment.getDoctor().getCpf() + "\","
                + "\"patientCpf\": \"" + appointment.getPatient().getCpf() + "\","
                + "\"date\": \"" + appointment.getDate() + "\""
                + "}";
        return ConnectionDao.makePostRequest(ENDPOINT, jsonData);
    }
    
    public static String getAppointment(String id) throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT + "/" + id);
    }
    
    public static void updateAppointment(String id, Appointment appointment) throws IOException {
        String jsonData = "{"
                + "\"doctorCpf\": \"" + appointment.getDoctor().getCpf() + "\","
                + "\"patientCpf\": \"" + appointment.getPatient().getCpf() + "\","
                + "\"date\": \"" + appointment.getDate() + "\""
                + "}";
        ConnectionDao.makePutRequest(ENDPOINT + "/" + id, jsonData);    
    }
    
    public static void deleteAppointment(String id) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + id);
    }
}
