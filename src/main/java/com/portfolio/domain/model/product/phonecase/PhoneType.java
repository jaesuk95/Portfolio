package com.portfolio.domain.model.product.phonecase;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PhoneType {

    APPLE("Apple"),
    GALAXY("Galaxy"),
    NOTHING("Nothing"),
    ONEPLUS("OnePlus");

    private final String name;

    PhoneType(String name) {
        this.name = name;
    }

    @JsonValue
    public String toString() {
        return name;
    }

}
