package com.marina.dao.impl;

import java.io.IOException;

import com.marina.dao.ConnectionDao;
import com.marina.dao.interfaces.PatientDao;
import com.marina.model.Patient;

public class PatientDaoImpl implements PatientDao {
    private static final String ENDPOINT = "paciente";

    @Override
    public String createPatient(Patient patient) throws IOException {
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

    @Override
    public void updatePatient(Patient patient) throws IOException {
        String jsonData = "{"
                + "\"name\": \"" + patient.getName() + "\","
                + "\"phone\": \"" + patient.getPhone() + "\","
                + "\"status\": \"" + patient.getStatus().toString() + "\","
                + "\"userAutomation\": \"" + patient.getUserAutomation().toString() + "\""
                + "}";
        ConnectionDao.makePutRequest(ENDPOINT + "/" + patient.getCpf(), jsonData);
    }

    @Override
    public void deletePatient(String cpf) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + cpf);
    }

    @Override
    public String listPatients() throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT);
    }
}
