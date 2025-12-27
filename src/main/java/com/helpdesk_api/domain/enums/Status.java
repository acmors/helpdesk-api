package com.helpdesk_api.domain.enums;

public enum Status {
    OPEN("OPEN"),
    PENDING("PENDING"),
    IN_PROGRESS("IN PROGRESS"),
    RESOLVED("RESOLVED"),
    CANCELED("CANCELED");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
