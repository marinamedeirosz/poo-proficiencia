package com.marina.enums;

public enum AppointmentStatus {
    AGENDADA("A"),  
    CANCELADA("C"),
    REALIZADA("R");

    private final String code;

    AppointmentStatus(String code) {
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
