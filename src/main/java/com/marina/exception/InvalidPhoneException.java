package com.marina.exception;

public class InvalidPhoneException extends IllegalArgumentException {
    public InvalidPhoneException(String message) {
        super("Telefone inválido: " + message);
    }
}
