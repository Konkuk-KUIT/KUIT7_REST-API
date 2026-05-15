package com.kuit.baemin.domain.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AddressType {
    home,
    company,
    etc;

    @JsonCreator
    public static AddressType from(String value) {
        if (value == null) {
            return null;
        }
        return AddressType.valueOf(value.toLowerCase());
    }

    @JsonValue
    public String getValue() {
        return name();
    }
}
