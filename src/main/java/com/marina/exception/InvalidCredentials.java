package com.marina.exception;

public class InvalidCredentials extends Exception {
    public InvalidCredentials(String message) {
        super("Erro ao autenticar: " + message);
    }
}
