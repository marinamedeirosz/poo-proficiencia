package com.marina.dao;

import java.io.IOException;

public class AuthDao {
    private static final String ENDPOINT = "auth";

    public static String login(String login, String password) throws IOException {
        return ConnectionDao.makePostRequest(ENDPOINT, "{\"login\": \"" + login + "\", \"password\": \"" + password + "\"}");
    }
}
