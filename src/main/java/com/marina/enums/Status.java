package com.marina.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.marina.exception.InvalidStatusException;

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

    public String getText() {
        switch (this) {
            case ATIVO:
                return "Ativo";
            case INATIVO:
                return "Inativo";
            default:
                return "";
        }
    }

    @JsonValue
    @Override
    public String toString() {
        return code;
    }

    @JsonCreator
    public static Status fromValue(String value) {
        for (Status status : Status.values()) {
            if (status.code.equalsIgnoreCase(value)) {
                return status;
            }
        }

        throw new InvalidStatusException(value);
    }
}
