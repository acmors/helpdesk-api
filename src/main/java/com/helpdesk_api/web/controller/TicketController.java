package com.helpdesk_api.web.controller;

import com.helpdesk_api.domain.Ticket;
import com.helpdesk_api.domain.enums.Status;
import com.helpdesk_api.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Ticket ticket){
        service.create(ticket);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> findAll(){
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> changeTicketStatus(@PathVariable("id") Long id, @RequestBody Status status){
       service.changeTicketStatus(id, status);
       return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ticket/{ticketId}/group/{groupId}")
    public ResponseEntity<Void> changeTicketGroup(@PathVariable("ticketId") Long ticketId, @PathVariable("groupId") Long groupId){
        service.assignTicketToGroup(ticketId, groupId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ticket/{ticketId}/analyst/{analystId}")
    public ResponseEntity<Void> changeTicketAnalyst(@PathVariable("ticketId") Long ticketId, @PathVariable("analystId") Long analystId){
        service.assignTicketToAnalyst(ticketId, analystId);
        return ResponseEntity.noContent().build();
    }
}
