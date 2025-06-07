package com.marina.dao.impl;

import java.io.IOException;

import com.marina.dao.ConnectionDao;
import com.marina.dao.interfaces.SpecialtyDao;
import com.marina.model.Specialty;

public class SpecialtyDaoImpl implements SpecialtyDao {
    private static final String ENDPOINT = "especialidade";

    @Override
    public String createSpecialty(Specialty specialty) throws IOException {
        String jsonData = "{"
                + "\"description\": \"" + specialty.getDescription() + "\""
                + "}";
        return ConnectionDao.makePostRequest(ENDPOINT, jsonData);
    }

    @Override
    public String listSpecialties() throws IOException {
        return ConnectionDao.makeGetRequest(ENDPOINT);
    }
}
