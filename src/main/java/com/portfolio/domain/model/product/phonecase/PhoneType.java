package com.portfolio.domain.model.product.phonecase;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PhoneType {

    APPLE("apple"),
    GALAXY("galaxy"),
    ONEPLUS("onePlus");

    private final String name;

    PhoneType(String name) {
        this.name = name;
    }

    @JsonValue
    public String toString() {
        return name;
    }

}
