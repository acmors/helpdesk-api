package com.helpdesk_api.web.dto.ticket;

import com.helpdesk_api.domain.enums.Priority;
import com.helpdesk_api.domain.enums.Status;

import java.time.Instant;

public record TicketResponse (
        String ticketNumber,
        String title,
        String description,
        Status status,
        Priority priority,
        String groupName,
        String analystName,
        Instant createdAt,
        Instant updatedAt,
        String createdBy,
        String updatedBy)
{
}
