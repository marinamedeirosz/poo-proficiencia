package com.marina.services;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.marina.dao.AuthDao;
import com.marina.utils.ReadValues;

public class AuthServiceTest {
    @Mock
    private AuthDao authDao;

    private String testUser;
    private String testPassword;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = "john.doe";
        testPassword = "password123";
    }

    @Test
    public void testLogin_Success() throws IOException {
        when(ReadValues.readString(anyString())).thenReturn("john.doe");
        when(ReadValues.readString(anyString())).thenReturn("password123");
        when(AuthDao.login(anyString(), anyString())).thenReturn(testUser);

        String result = AuthService.login();

        assertNotNull(result);
        assertEquals(testUser, result);
        verify(authDao);
        AuthDao.login(testUser, testPassword);
    }

    @Test(expected = IOException.class)
    public void testLogin_Failed() throws IOException {
        when(ReadValues.readString(anyString())).thenReturn("john.doe");
        when(ReadValues.readString(anyString())).thenReturn("wrongpassword");
        when(AuthDao.login(anyString(), anyString())).thenThrow(new IOException("Credenciais inválidas! Verifique-as e tente novamente."));

        AuthService.login();
    }

    @Test
    public void testRegister_Success() throws IOException {
        when(ReadValues.readName(anyString())).thenReturn("John Doe");
        when(ReadValues.readCpf(anyString())).thenReturn("54055874006");
        when(ReadValues.readString(anyString())).thenReturn("password123");
        when(AuthDao.register(anyString(), anyString())).thenReturn("Usuário criado com sucesso!");

        String result = AuthService.register();

        assertNotNull(result);
        assertTrue(result.contains("sucesso"));
        verify(authDao);
        AuthDao.register(testUser, testPassword);
    }

    @Test(expected = IOException.class)
    public void testRegister_Failed() throws IOException {
        when(ReadValues.readName(anyString())).thenReturn("John Doe");
        when(ReadValues.readString(anyString())).thenReturn("john.doe");
        when(ReadValues.readString(anyString())).thenReturn("password123");
        when(AuthDao.register(anyString(), anyString())).thenThrow(new IOException("Registration failed"));

        AuthService.register();
    }
}
