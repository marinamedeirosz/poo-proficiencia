package com.marina.services;

import java.io.IOException;

import com.marina.dao.interfaces.AuthDao;
import com.marina.utils.ReadValues;

public class AuthService {

    private final AuthDao authDao;

    // Constructor injection
    public AuthService(AuthDao authDao) {
        this.authDao = authDao;
    }

    public String login() throws IOException {
        String login = ReadValues.readString("Digite o login: ");
        String password = ReadValues.readString("Digite a senha: ");

        return authDao.login(login, password);
    }

    public String register() throws IOException {
        String login = ReadValues.readString("Digite o login: ");
        String password = ReadValues.readString("Digite a senha: ");

        return authDao.register(login, password);
    }
}
