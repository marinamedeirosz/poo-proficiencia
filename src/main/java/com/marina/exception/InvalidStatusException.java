package com.marina.exception;

public class InvalidStatusException extends IllegalArgumentException {
    public InvalidStatusException(String message) {
        super("Status inválido: " + message);
    }
}
