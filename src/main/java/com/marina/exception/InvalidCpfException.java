package com.marina.exception;

public class InvalidCpfException extends IllegalArgumentException {
    public InvalidCpfException(String message) {
        super("CPF inválido: " + message);
    }
}
