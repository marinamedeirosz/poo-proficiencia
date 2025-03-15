package com.marina.dao;

import java.io.IOException;

import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;

public class PatientDao {
    private static final String ENDPOINT = "paciente";

    public static String createPatient(String name, String cpf, String phone, Profile profile, Status status, YesOrNo userAutomation) throws IOException {
        String jsonData = "{"
                + "\"name\": \"" + name + "\","
                + "\"cpf\": \"" + cpf + "\","
                + "\"phone\": \"" + phone + "\","
                + "\"profile\": \"" + profile.getCode() + "\","
                + "\"status\": \"" + status.getCode() + "\","
                + "\"userAutomation\": \"" + userAutomation.getCode() + "\""
                + "}";
        return ConnectionDao.makePostRequest(ENDPOINT, jsonData);
    }

    public static String getPatient(String cpf) throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT + "/" + cpf);
    }

    public static void updatePatient(String name, String cpf, String phone, Profile profile, Status status, YesOrNo userAutomation) throws IOException {
        String jsonData = "{"
                + "\"name\": \"" + name + "\","
                + "\"cpf\": \"" + cpf + "\","
                + "\"phone\": \"" + phone + "\","
                + "\"profile\": \"" + profile.getCode() + "\","
                + "\"status\": \"" + status.getCode() + "\","
                + "\"userAutomation\": \"" + userAutomation.getCode() + "\""
                + "}";
        ConnectionDao.makePutRequest(ENDPOINT + "/" + cpf, jsonData);
    }
    
    public static void deletePatient(String cpf) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + cpf);
    }
}
