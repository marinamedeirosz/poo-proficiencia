package com.marina.model;

import java.util.Date;

public abstract class BaseEntity {
    private final int id;
    private final Date createdDate;

    public BaseEntity(int id, Date createdDate) {
        this.id = id;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public abstract String getEntityDetails();
}
