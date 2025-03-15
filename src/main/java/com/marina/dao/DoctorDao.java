package com.marina.dao;

import java.io.IOException;

import com.marina.model.Doctor;

public class DoctorDao {
    private static final String ENDPOINT = "pessoa";

    public static String createDoctor(Doctor doc) throws IOException {
        String jsonData = "{"
                + "\"name\": \"" + doc.getName() + "\","
                + "\"cpf\": \"" + doc.getCpf() + "\","
                + "\"phone\": \"" + doc.getPhone() + "\","
                + "\"profile\": \"" + doc.getProfile().getCode() + "\","
                + "\"status\": \"" + doc.getStatus().getCode() + "\","
                + "\"userAutomation\": \"" + doc.getUserAutomation().getCode() + "\""
                + "}";
        return ConnectionDao.makePostRequest(ENDPOINT, jsonData);
    }

    public static String getDoctor(String cpf) throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT + "/" + cpf);
    }
    
    public static void updateDoctor(Doctor doc) throws IOException {
        String jsonData = "{"
                + "\"name\": \"" + doc.getName() + "\","
                + "\"phone\": \"" + doc.getPhone() + "\","
                + "\"status\": \"" + doc.getStatus().getCode() + "\","
                + "\"userAutomation\": \"" + doc.getUserAutomation().getCode() + "\","
                + "\"crm\": \"" + doc.getCrm() + "\""
                + "}";
        ConnectionDao.makePutRequest(ENDPOINT + "/" + doc.getCpf(), jsonData);
    }
    
    public static void deleteDoctor(String cpf) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + cpf);
    }
}
