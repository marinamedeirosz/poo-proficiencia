package com.marina.exception;

public class InvalidYesOrNoException extends IllegalArgumentException {
    public InvalidYesOrNoException(String message) {
        super("Sim ou não inválido: " + message);
    }
}
