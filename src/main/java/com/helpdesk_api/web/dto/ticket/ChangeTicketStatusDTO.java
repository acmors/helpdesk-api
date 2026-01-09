package com.helpdesk_api.web.dto.ticket;

import com.helpdesk_api.domain.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeTicketStatusDTO {
    private Status status;
}
