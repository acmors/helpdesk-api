package com.helpdesk_api.web.dto.ticket;

import lombok.*;

public record TicketRequest(String title, String description, String groupName){

}
