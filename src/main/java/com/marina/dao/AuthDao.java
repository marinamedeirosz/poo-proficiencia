package com.marina.dao;

import java.io.IOException;

public class AuthDao {
    public static String login(String login, String password) throws IOException {
        return ConnectionDao.makePostRequest("auth", "{\"login\": \"" + login.trim() + "\", \"password\": \"" + password.trim() + "\"}");
    }

    public static String register(String login, String password) throws IOException {
        return ConnectionDao.makePostRequest("register", "{\"login\": \"" + login.trim() + "\", \"password\": \"" + password.trim() + "\"}");
    }
}
