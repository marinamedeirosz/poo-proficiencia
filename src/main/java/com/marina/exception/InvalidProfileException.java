package com.marina.exception;

public class InvalidProfileException extends IllegalArgumentException {
    public InvalidProfileException(String message) {
        super("Perfil inválido: " + message);
    }
}
