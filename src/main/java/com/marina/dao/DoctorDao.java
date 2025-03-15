package com.marina.dao;

import java.io.IOException;

import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;

public class DoctorDao {
    private static final String ENDPOINT = "pessoa";

    public static String createDoctor(String name, String cpf, String phone, Profile profile, Status status, YesOrNo userAutomation) throws IOException {
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

    public static String getDoctor(String cpf) throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT + "/" + cpf);
    }
    
    public static void updateDoctor(String name, String cpf, String phone, Status status, YesOrNo userAutomation, String crm) throws IOException {
        String jsonData = "{"
                + "\"name\": \"" + name + "\","
                + "\"phone\": \"" + phone + "\","
                + "\"status\": \"" + status.getCode() + "\","
                + "\"userAutomation\": \"" + userAutomation.getCode() + "\","
                + "\"crm\": \"" + crm + "\""
                + "}";
        ConnectionDao.makePutRequest(ENDPOINT + "/" + cpf, jsonData);
    }
    
    public static void deleteDoctor(String cpf) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + cpf);
    }
}
