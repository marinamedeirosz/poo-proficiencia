package com.marina.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.marina.exception.InvalidYesOrNoException;

public enum YesOrNo {
    SIM("S"),
    NAO("N");

    private final String code;

    YesOrNo(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @JsonValue
    @Override
    public String toString() {
        return code;
    }

    @JsonCreator
    public static YesOrNo fromValue(String value) {
        for (YesOrNo yesOrNo : YesOrNo.values()) {
            if (yesOrNo.code.equals(value)) {
                return yesOrNo;
            }
        }
        throw new InvalidYesOrNoException(value);
    }
}
