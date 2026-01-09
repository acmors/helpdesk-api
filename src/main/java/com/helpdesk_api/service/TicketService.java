package com.helpdesk_api.service;

import com.helpdesk_api.domain.Group;
import com.helpdesk_api.domain.Ticket;
import com.helpdesk_api.domain.TicketSequence;
import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.domain.enums.Priority;
import com.helpdesk_api.domain.enums.Status;
import com.helpdesk_api.domain.enums.TicketType;
import com.helpdesk_api.exception.InvalidTicketStatusException;
import com.helpdesk_api.exception.ResourceNotFoundException;
import com.helpdesk_api.repository.TicketRepository;
import com.helpdesk_api.repository.TicketSequenceRepository;
import com.helpdesk_api.web.dto.ticket.TicketResponse;
import com.helpdesk_api.web.dto.ticket.TicketToDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository repository;
    private final GroupService groupService;
    private final UserService userService;
    private final TicketSequenceRepository sequenceRepository;

    @Transactional
    public TicketResponse create(Ticket ticket){
        ticket.setStatus(Status.OPEN);
        ticket.setPriority(Priority.P3);

        String ticketNumber = generateTicketNumber(ticket.getTicketType());
        ticket.setTicketNumber(ticketNumber);
        Ticket createTicket = repository.save(ticket);

        return TicketToDTOMapper.toResponse(createTicket);
    }

    @Transactional(readOnly = true)
    public List<TicketResponse> list(){

        return repository.findAll()
                .stream()
                .map(TicketToDTOMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Ticket findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @Transactional(readOnly = true)
    public Ticket findByTicketEntityNumber(String ticketNumber){
        return repository.findByTicketNumber(ticketNumber)
                .orElseThrow(() -> new ResourceNotFoundException("TICKET NOT FOUND"));
    }

    @Transactional(readOnly = true)
    public TicketResponse findByTicketNumber(String ticketNumber){
        return TicketToDTOMapper.toResponse(
                findByTicketEntityNumber(ticketNumber)
        );
    }

    @Transactional
    public void assignTicketToGroup(String ticketNumber, Long groupId){
        Ticket ticket = findByTicketEntityNumber(ticketNumber);
        Group group = groupService.findById(groupId);

        if (ticket.getStatus() == Status.RESOLVED || ticket.getStatus() == Status.CANCELED){
            throw new InvalidTicketStatusException("Only OPEN tickets can receive group.");
        }

        ticket.setGroup(group);
        repository.save(ticket);
    }

    @Transactional
    public void assignTicketToAnalyst(String ticketNumber, Long analystId){
        Ticket ticket = findByTicketEntityNumber(ticketNumber);
        UserAccount userAccount = userService.findById(analystId);

        if (ticket.getStatus() == Status.RESOLVED || ticket.getStatus() == Status.CANCELED){
            throw new InvalidTicketStatusException("Only OPEN tickets can receive Analyst.");
        }

        ticket.setUserAccount(userAccount);
        ticket.setStatus(Status.IN_PROGRESS);
        repository.save(ticket);
    }

    @Transactional
    public void changeTicketStatus(String ticketNumber, Status status){

        Ticket ticket = findByTicketEntityNumber(ticketNumber);

        if (ticket.getStatus() == Status.RESOLVED || ticket.getStatus() == Status.CANCELED){
            throw new InvalidTicketStatusException("Only OPEN tickets can be updated.");
        }

        if (status == Status.RESOLVED && ticket.getUserAccount() == null) {
            throw new InvalidTicketStatusException("Ticket must be received by an analyst to be resolved.");
        }

        ticket.setStatus(status);
        repository.save(ticket);
    }


    private String generateTicketNumber(TicketType type){
        TicketSequence sequence = sequenceRepository.findById(type)
                .orElseThrow(() -> new RuntimeException("Sequence Not Configured."));

        Long value = sequence.getNextValue();
        sequence.setNextValue(value + 1);

        String prefix = type == TicketType.INCIDENT ? "INC" : "REQ";
        return  prefix + "-" + String.format("%06d", value);
    }

}
