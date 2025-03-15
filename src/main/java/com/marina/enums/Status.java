package com.marina.enums;

public enum Status {
    ATIVO("A"),  
    INATIVO("I");

    private final String code;

    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
