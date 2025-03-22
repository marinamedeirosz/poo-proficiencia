package com.marina.services;

import java.io.IOException;

import com.marina.dao.AuthDao;
import com.marina.utils.ReadValues;

public class AuthService {
    public static String login() throws IOException {
        String login = ReadValues.readString("Digite o login: ");
        String password = ReadValues.readString("Digite a senha: ");

        String response = AuthDao.login(login, password);
        return response;
    }

    public static String register() throws IOException {
        String login = ReadValues.readString("Digite o login: ");
        String password = ReadValues.readString("Digite a senha: ");
    
        String response = AuthDao.register(login, password);
        return response;
    }
}
