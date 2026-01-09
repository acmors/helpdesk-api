package com.helpdesk_api.repository;

import com.helpdesk_api.domain.TicketSequence;
import com.helpdesk_api.domain.enums.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketSequenceRepository extends JpaRepository<TicketSequence, TicketType> {
}
