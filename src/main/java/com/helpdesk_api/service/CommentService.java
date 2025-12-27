package com.helpdesk_api.service;

import com.helpdesk_api.domain.Comment;
import com.helpdesk_api.domain.Ticket;
import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.repository.CommentRepository;
import com.helpdesk_api.repository.TicketRepository;
import com.helpdesk_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CommentService {

    private final CommentRepository repository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository repository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.repository = repository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Comment create(Long ticketId, Long userId, Comment comment){
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found."));
        UserAccount userAccount = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        comment.setTicket(ticket);
        comment.setUser(userAccount);
        comment.setCreatedAt(Instant.now());

        return repository.save(comment);


    }
}
