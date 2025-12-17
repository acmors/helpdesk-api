package com.helpdesk_api.domain.enums;

public enum Priority {
    P1("P1"),
    P2("P2"),
    P3("P3"),
    P4("P4");

    private final String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }
}
