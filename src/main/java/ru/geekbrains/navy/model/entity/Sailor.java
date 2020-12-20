package ru.geekbrains.navy.model.entity;

import ru.geekbrains.navy.model.enums.SailorStatusType;

public class Sailor {
    private long id;
    private String name;
    private Long skipperId;
    private SailorStatusType status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSkipperId() {
        return skipperId;
    }

    public void setSkipperId(Long sailorId) {
        this.skipperId = sailorId;
    }

    public SailorStatusType getStatus() {
        return status;
    }

    public void setStatus(SailorStatusType status) {
        this.status = status;
    }
}
