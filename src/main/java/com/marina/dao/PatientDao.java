package com.marina.dao;

import java.io.IOException;

import com.marina.model.Patient;

public class PatientDao {
    private static final String ENDPOINT = "pessoa";

    public static String createPatient(Patient patient) throws IOException {
        String jsonData = "{"
                + "\"name\": \"" + patient.getName() + "\","
                + "\"cpf\": \"" + patient.getCpf() + "\","
                + "\"phone\": \"" + patient.getPhone() + "\","
                + "\"profile\": \"" + patient.getProfile().getCode() + "\","
                + "\"status\": \"" + patient.getStatus().getCode() + "\","
                + "\"userAutomation\": \"" + patient.getUserAutomation().getCode() + "\""
                + "}";
        return ConnectionDao.makePostRequest(ENDPOINT, jsonData);
    }

    public static String getPatient(String cpf) throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT + "/" + cpf);
    }

    public static void updatePatient(Patient patient) throws IOException {
        String jsonData = "{"
                + "\"name\": \"" + patient.getName() + "\","
                + "\"phone\": \"" + patient.getPhone() + "\","
                + "\"status\": \"" + patient.getStatus().getCode() + "\","
                + "\"userAutomation\": \"" + patient.getUserAutomation().getCode() + "\""
                + "}";
        ConnectionDao.makePutRequest(ENDPOINT + "/" + patient.getCpf(), jsonData);
    }
    
    public static void deletePatient(String cpf) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + cpf);
    }
}
