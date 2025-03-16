package com.marina.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.marina.exception.InvalidProfileException;

public enum Profile {
    MEDICO("M"),
    PACIENTE("P");

    private final String value;

    Profile(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Profile fromValue(String value) {
        for (Profile profile : Profile.values()) {
            if (profile.value.equals(value)) {
                return profile;
            }
        }
        throw new InvalidProfileException(value);
    }
}
