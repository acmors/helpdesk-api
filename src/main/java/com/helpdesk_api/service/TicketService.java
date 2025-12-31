package com.helpdesk_api.service;

import com.helpdesk_api.domain.Group;
import com.helpdesk_api.domain.Ticket;
import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.domain.enums.Priority;
import com.helpdesk_api.domain.enums.Status;
import com.helpdesk_api.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository repository;
    private final GroupService groupService;
    private final UserService userService;

    @Transactional
    public Ticket create(Ticket newTicket){
        newTicket.setStatus(Status.OPEN);
        newTicket.setPriority(Priority.P3);
        newTicket.setCreatedAt(Instant.now());

        return repository.save(newTicket);
    }

    @Transactional(readOnly = true)
    public List<Ticket> list(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Ticket findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Transactional
    public void assignTicketToGroup(Long ticketId, Long groupId){
        Ticket ticket = findById(ticketId);
        Group group = groupService.findById(groupId);

        if (ticket.getStatus() == Status.RESOLVED || ticket.getStatus() == Status.CANCELED){
            throw new RuntimeException("Only OPEN tickets can receive group.");
        }

        ticket.setGroup(group);
        repository.save(ticket);
    }

    @Transactional
    public void assignTicketToAnalyst(Long id, Long analystId){
        Ticket ticket = findById(id);
        UserAccount userAccount = userService.findById(analystId);

        if (ticket.getStatus() == Status.RESOLVED || ticket.getStatus() == Status.CANCELED){
            throw new RuntimeException("Only OPEN tickets can receive Analyst.");
        }

        ticket.setUserAccount(userAccount);
        repository.save(ticket);
    }

    @Transactional
    public void changeTicketStatus(Long id, Status status){

        Ticket ticket = findById(id);

        if (ticket.getStatus() == Status.RESOLVED || ticket.getStatus() == Status.CANCELED){
            throw new RuntimeException("Only OPEN tickets can be updated.");
        }

        ticket.setStatus(status);
        repository.save(ticket);
    }


}
