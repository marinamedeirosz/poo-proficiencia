package com.marina.exception;

public class InvalidPhoneException extends RuntimeException {
    public InvalidPhoneException(String message) {
        super("Telefone inválido: " + message);
    }
}
