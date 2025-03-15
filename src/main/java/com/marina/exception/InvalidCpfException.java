package com.marina.exception;

public class InvalidCpfException extends RuntimeException {
    public InvalidCpfException(String message) {
        super("CPF inválido: " + message);
    }
}
