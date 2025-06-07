package com.marina.dao.impl;

import java.io.IOException;

import com.marina.dao.ConnectionDao;
import com.marina.dao.interfaces.DoctorDao;
import com.marina.model.Doctor;

public class DoctorDaoImpl implements DoctorDao {
    private static final String ENDPOINT = "medico";
    private static final String SUBENDPOINT = "especialidade";

    @Override
    public String createDoctor(Doctor doc) throws IOException {
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

    @Override
    public String getDoctor(String cpf) throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT + "/" + cpf);
    }

    @Override
    public void updateDoctor(Doctor doc) throws IOException {
        String jsonData = "{"
                + "\"name\": \"" + doc.getName() + "\","
                + "\"phone\": \"" + doc.getPhone() + "\","
                + "\"status\": \"" + doc.getStatus().toString() + "\","
                + "\"userAutomation\": \"" + doc.getUserAutomation().toString() + "\","
                + "\"crm\": \"" + doc.getCrm() + "\""
                + "}";
        ConnectionDao.makePutRequest(ENDPOINT + "/" + doc.getCpf(), jsonData);
    }

    @Override
    public void deleteDoctor(String cpf) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + cpf);
    }

    @Override
    public String listDoctors() throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT);
    }

    @Override
    public String addSpecialty(String crm, String specialty, Integer level) throws IOException {
        String jsonData = "{"
                + "\"speId\": \"" + specialty + "\","
                + "\"speLvl\": \"" + level + "\""
                + "}";
        return ConnectionDao.makePostRequest(ENDPOINT + "/" + crm + "/" + SUBENDPOINT, jsonData);
    }

    @Override
    public String listSpecialties(String crm) throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT + "/" + crm + "/" + SUBENDPOINT);
    }
}
