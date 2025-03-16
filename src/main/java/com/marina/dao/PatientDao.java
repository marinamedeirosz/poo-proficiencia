package com.marina.dao;

import java.io.IOException;

import com.marina.model.Patient;

public class PatientDao {
    private static final String ENDPOINT = "paciente";

    public static String createPatient(Patient patient) throws IOException {
        String jsonData = "{"
                + "\"name\": \"" + patient.getName() + "\","
                + "\"cpf\": \"" + patient.getCpf() + "\","
                + "\"phone\": \"" + patient.getPhone() + "\","
                + "\"profile\": \"" + patient.getProfile().getValue() + "\","
                + "\"status\": \"" + patient.getStatus().toString() + "\","
                + "\"userAutomation\": \"" + patient.getUserAutomation().toString() + "\""
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
                + "\"status\": \"" + patient.getStatus().toString() + "\","
                + "\"userAutomation\": \"" + patient.getUserAutomation().toString() + "\""
                + "}";
        ConnectionDao.makePutRequest(ENDPOINT + "/" + patient.getCpf(), jsonData);
    }
    
    public static void deletePatient(String cpf) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + cpf);
    }

    public static String listPatients() throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT);
    }
}
