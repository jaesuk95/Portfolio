package com.portfolio.utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
public class IpAddress implements Serializable {

    private static final long serialVersionUID = -2299506924260669371L;

    private String value;

    public IpAddress(String value) {
        this.value = value == null ? "" : value;
    }
}
