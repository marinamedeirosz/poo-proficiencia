package com.marina.dao;

import java.io.IOException;

import com.marina.model.Doctor;

public class DoctorDao {
    private static final String ENDPOINT = "medico";
    private static final String SUBENDPOINT = "especialidade";

    public static String createDoctor(Doctor doc) throws IOException {
        String jsonData = "{"
                + "\"name\": \"" + doc.getName() + "\","
                + "\"cpf\": \"" + doc.getCpf() + "\","
                + "\"phone\": \"" + doc.getPhone() + "\","
                + "\"profile\": \"" + doc.getProfile().getValue() + "\","
                + "\"status\": \"" + doc.getStatus().toString() + "\","
                + "\"crm\": \"" + doc.getCrm() + "\","
                + "\"userAutomation\": \"" + doc.getUserAutomation().toString() + "\""
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
                + "\"status\": \"" + doc.getStatus().toString() + "\","
                + "\"userAutomation\": \"" + doc.getUserAutomation().toString() + "\","
                + "\"crm\": \"" + doc.getCrm() + "\""
                + "}";
        ConnectionDao.makePutRequest(ENDPOINT + "/" + doc.getCpf(), jsonData);
    }
    
    public static void deleteDoctor(String cpf) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + cpf);
    }

    public static String listDoctors() throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT);
    }

    public static String addSpecialty(String crm, String specialty, Integer level) throws IOException {
        String jsonData = "{"
                + "\"speId\": \"" + specialty + "\","
                + "\"speLvl\": \"" + level + "\""
                + "}";
        return ConnectionDao.makePostRequest(ENDPOINT + "/" + crm + "/" + SUBENDPOINT, jsonData);
    }

    public static String listSpecialties(String crm) throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT + "/" + crm + "/" + SUBENDPOINT);
    }
}
