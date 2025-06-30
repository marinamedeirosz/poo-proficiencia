package com.marina.tests;

import com.marina.dao.impl.AuthDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

public class AuthDaoTest {

    private AuthDaoImpl authDaoImpl;

    @BeforeEach
    void setUp() {
        authDaoImpl = new AuthDaoImpl();
    }

    @Test
    void shouldFailAuthentication() throws IOException {
        final String result = authDaoImpl.login("eike", "1234");

        Assertions.assertEquals("Credenciais inválidas! Verifique-as e tente novamente.", result);
    }

    @Test
    void shouldAuthenticate() throws IOException {
        final String result = authDaoImpl.login("eike", "123");

        Assertions.assertEquals("Autenticado com sucesso.", result);
    }

    @Test
    void shouldRegister() throws IOException {
        final String randUser = UUID.randomUUID().toString();
        final String randPassword = UUID.randomUUID().toString();

        final String result = authDaoImpl.register(randUser, randPassword);

        Assertions.assertEquals("Usuário criado com sucesso!", result);
    }

    @Test
    void shouldFailRegister() throws IOException {
        final String result = authDaoImpl.register("eike", "123");

        Assertions.assertNotEquals("Usuário criado com sucesso!", result);
    }

}
