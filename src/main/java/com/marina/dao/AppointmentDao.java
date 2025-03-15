package com.marina.dao;

import java.io.IOException;
import java.util.Date;

public class AppointmentDao {
    private static final String ENDPOINT = "consulta";

    public static String createAppointment(String doctorCpf, String patientCpf, Date date) throws IOException {
        String jsonData = "{"
                + "\"doctorCpf\": \"" + doctorCpf + "\","
                + "\"patientCpf\": \"" + patientCpf + "\","
                + "\"date\": \"" + date + "\""
                + "}";
        return ConnectionDao.makePostRequest(ENDPOINT, jsonData);
    }
    
    public static String getAppointment(String id) throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT + "/" + id);
    }
    
    public static void updateAppointment(String id, String doctorCpf, String patientCpf, String date) throws IOException {
        String jsonData = "{"
                + "\"doctorCpf\": \"" + doctorCpf + "\","
                + "\"patientCpf\": \"" + patientCpf + "\","
                + "\"date\": \"" + date + "\""
                + "}";
        ConnectionDao.makePutRequest(ENDPOINT + "/" + id, jsonData);    
    }
    
    public static void deleteAppointment(String id) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + id);
    }
}
