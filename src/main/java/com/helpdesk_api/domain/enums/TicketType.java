package com.helpdesk_api.domain.enums;

import lombok.Getter;

@Getter
public enum TicketType {
    INCIDENT("INCIDENT"),
    REQUEST("REQUEST");

    public final String TicketType;

    TicketType(String ticketType) {
        TicketType = ticketType;
    }
}
