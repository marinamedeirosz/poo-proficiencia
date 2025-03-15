package com.marina.enums;

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
    
    @Override
    public String toString() {
        return code;
    }
}