package com.marina.dao.impl;

import java.io.IOException;

import com.marina.dao.ConnectionDao;
import com.marina.dao.interfaces.AuthDao;

public class AuthDaoImpl implements AuthDao {

    private static final String AUTH_ENDPOINT = "auth";
    private static final String REGISTER_ENDPOINT = "register";

    private String createJson(String login, String password) {
        return "{\"login\": \"" + login.trim() + "\", \"password\": \"" + password.trim() + "\"}";
    }

    @Override
    public String login(String login, String password) throws IOException {
        return ConnectionDao.makePostRequest(AUTH_ENDPOINT, createJson(login, password));
    }

    @Override
    public String register(String login, String password) throws IOException {
        return ConnectionDao.makePostRequest(REGISTER_ENDPOINT, createJson(login, password));
    }
}
