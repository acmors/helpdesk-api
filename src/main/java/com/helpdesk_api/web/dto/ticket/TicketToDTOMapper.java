package com.helpdesk_api.web.dto.ticket;

import com.helpdesk_api.domain.Group;
import com.helpdesk_api.domain.Ticket;

public final class TicketToDTOMapper {

    private TicketToDTOMapper(){}

    public static Ticket toEntity(TicketRequest dto, Group group){
        Ticket ticket = new Ticket();
        ticket.setTitle(dto.title());
        ticket.setDescription(dto.description());
        ticket.setGroup(group);
        return ticket;
    }

    public static TicketResponse toResponse(Ticket ticket){
        String groupName = ticket.getGroup() != null ? ticket.getGroup().getName() : null;
        String analystName = ticket.getGroup() != null ? ticket.getGroup().getName() : null;

        return new TicketResponse(
                ticket.getTicketNumber(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                groupName,
                analystName,
                ticket.getCreatedAt(),
                ticket.getUpdatedAt(),
                ticket.getCreatedBy(),
                ticket.getUpdatedBy()
        );
    }
}
