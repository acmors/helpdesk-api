package com.helpdesk_api.service;

import com.helpdesk_api.domain.Ticket;
import com.helpdesk_api.domain.enums.Priority;
import com.helpdesk_api.domain.enums.Status;
import com.helpdesk_api.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Ticket create(Ticket newTicket){
        newTicket.setStatus(Status.OPEN);
        newTicket.setPriority(Priority.P3);
        newTicket.setCreatedAt(Instant.now());

        return repository.save(newTicket);
    }

    public List<Ticket> list(){
        return repository.findAll();
    }

    public Ticket findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
