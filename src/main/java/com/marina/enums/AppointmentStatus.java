package com.marina.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.marina.exception.InvalidStatusException;

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

    public String getText() {
        switch (this) {
            case AGENDADA:
                return "Agendada";
            case CANCELADA:
                return "Cancelada";
            case REALIZADA:
                return "Realizada";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return code;
    }

    @JsonValue
    public String getValue() {
        return code;
    }

    @JsonCreator
    public static AppointmentStatus fromValue(String value) {
        for (AppointmentStatus status : AppointmentStatus.values()) {
            if (status.code.equals(value)) {
                return status;
            }
        }
        throw new InvalidStatusException(value);
    }
}
