package com.helpdesk_api.domain;

import com.helpdesk_api.domain.enums.TicketType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketSequence {

    @Id
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @Column(nullable = false)
    private Long nextValue;
}
