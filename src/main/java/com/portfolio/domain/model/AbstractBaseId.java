package com.portfolio.domain.model;

import java.io.Serializable;

public abstract class AbstractBaseId implements Serializable {

    private static final long serialVersionUID = 7455203855453901831L;

    private long id;

    public AbstractBaseId(long id) {
        this.id = id;
    }

    public long value() {
        return id;
    }

    public boolean isValid() {
        return id > 0;
    }

}
