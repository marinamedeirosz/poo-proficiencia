package com.marina.exception;

public class CpfAlreadyExists extends Exception {
    public CpfAlreadyExists(String message) {
        super("CPF já cadastrado: " + message);
    }
}
