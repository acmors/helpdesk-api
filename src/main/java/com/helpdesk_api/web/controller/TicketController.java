package com.helpdesk_api.web.controller;

import com.helpdesk_api.domain.Ticket;
import com.helpdesk_api.service.TicketService;
import com.helpdesk_api.web.dto.ticket.ChangeTicketStatusDTO;
import com.helpdesk_api.web.dto.ticket.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService service;

    @PostMapping()
    public ResponseEntity<TicketResponse> create(@RequestBody Ticket ticket){
        service.create(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> findAll(){
        var list = service.list();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{ticketNumber}")
    public ResponseEntity<TicketResponse> findByTicketNumber(@PathVariable String ticketNumber){
        var ticket = service.findByTicketNumber(ticketNumber);
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

    @PatchMapping("/{ticketNumber}/status")
    public ResponseEntity<Void> changeTicketStatus(@PathVariable String ticketNumber, @RequestBody ChangeTicketStatusDTO request){
       service.changeTicketStatus(ticketNumber, request.getStatus());
       return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ticket/{ticketNumber}/group/{groupId}")
    public ResponseEntity<Void> changeTicketGroup(@PathVariable String ticketNumber, @PathVariable("groupId") Long groupId){
        service.assignTicketToGroup(ticketNumber, groupId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ticket/{ticketNumber}/analyst/{analystId}")
    public ResponseEntity<Void> changeTicketAnalyst(@PathVariable String ticketNumber, @PathVariable Long analystId){
        service.assignTicketToAnalyst(ticketNumber, analystId);
        return ResponseEntity.noContent().build();
    }
}
