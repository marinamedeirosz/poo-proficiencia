package com.marina.dao;

import java.io.IOException;

import com.marina.model.Specialty;

public class SpecialtyDao {
    private static final String ENDPOINT = "especialidade";

    public static String createSpecialty(Specialty specialty) throws IOException {
        String jsonData = "{"
                + "\"description\": \"" + specialty.getDescription() + "\""
                + "}";
        return ConnectionDao.makePostRequest(ENDPOINT, jsonData);
    }
    
    public static void deleteSpecialty(Integer id) throws IOException {
        ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + id);
    }

    public static String listSpecialties() throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT);
    }
}
