package com.marina.services;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mockStatic;

import com.marina.dao.AuthDao;
import com.marina.utils.ReadValues;

class AuthServiceTest {

    @Test
    @DisplayName("Should login successfully with valid credentials")
    void loginSuccessfully() throws IOException {
        try (MockedStatic<ReadValues> readValuesMock = mockStatic(ReadValues.class);
             MockedStatic<AuthDao> authDaoMock = mockStatic(AuthDao.class)) {

            // Arrange
            readValuesMock.when(() -> ReadValues.readString("Digite o login: ")).thenReturn("m.medeiros");
            readValuesMock.when(() -> ReadValues.readString("Digite a senha: ")).thenReturn("123");
            authDaoMock.when(() -> AuthDao.login("m.medeiros", "123")).thenReturn("Autenticado com sucesso.");

            // Act
            String result = AuthService.login();

            // Assert
            assertEquals("Autenticado com sucesso.", result);
            readValuesMock.verify(() -> ReadValues.readString("Digite o login: "));
            readValuesMock.verify(() -> ReadValues.readString("Digite a senha: "));
            authDaoMock.verify(() -> AuthDao.login("m.medeiros", "123"));
        }
    }

    @Test
    @DisplayName("Should register successfully with valid credentials")
    void registerSuccessfully() throws IOException {
        try (MockedStatic<ReadValues> readValuesMock = mockStatic(ReadValues.class);
             MockedStatic<AuthDao> authDaoMock = mockStatic(AuthDao.class)) {

            // Arrange
            readValuesMock.when(() -> ReadValues.readString("Digite o login: ")).thenReturn("newuser");
            readValuesMock.when(() -> ReadValues.readString("Digite a senha: ")).thenReturn("newpass");
            authDaoMock.when(() -> AuthDao.register("newuser", "newpass")).thenReturn("Usuário criado com sucesso!");

            // Act
            String result = AuthService.register();

            // Assert
            assertEquals("Usuário criado com sucesso!", result);
            readValuesMock.verify(() -> ReadValues.readString("Digite o login: "));
            readValuesMock.verify(() -> ReadValues.readString("Digite a senha: "));
            authDaoMock.verify(() -> AuthDao.register("newuser", "newpass"));
        }
    }
}
