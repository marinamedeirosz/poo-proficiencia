package com.marina.enums;

public enum Profile {
    MEDICO("M"),
    PACIENTE("P");

    private final String code;

    Profile(String code) {
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
