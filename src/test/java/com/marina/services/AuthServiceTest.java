package com.marina.services;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.marina.dao.impl.AuthDaoImpl;
import com.marina.utils.ReadValues;

class AuthServiceTest {

    private AuthDaoImpl authDaoImplMock;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        authDaoImplMock = mock(AuthDaoImpl.class);
        authService = new AuthService(authDaoImplMock);
    }

    @Test
    @DisplayName("Should login successfully with valid credentials")
    void loginSuccessfully() throws IOException {
        when(ReadValues.readString("Digite o login: ")).thenReturn("m.medeiros");
        when(ReadValues.readString("Digite a senha: ")).thenReturn("123");

        when(authDaoImplMock.login("m.medeiros", "123")).thenReturn("Autenticado com sucesso.");

        String result = authService.login();

        assertEquals("Autenticado com sucesso.", result);

        verify(authDaoImplMock).login("m.medeiros", "123");
    }

    @Test
    @DisplayName("Should register successfully with valid credentials")
    void registerSuccessfully() throws IOException {
        when(ReadValues.readString("Digite o login: ")).thenReturn("newuser");
        when(ReadValues.readString("Digite a senha: ")).thenReturn("newpass");

        when(authDaoImplMock.register("newuser", "newpass")).thenReturn("Usuário criado com sucesso!");

        String result = authService.register();

        assertEquals("Usuário criado com sucesso!", result);

        verify(authDaoImplMock).register("newuser", "newpass");
    }
}
