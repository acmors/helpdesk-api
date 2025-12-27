package com.helpdesk_api.service;

import com.helpdesk_api.domain.Comment;
import com.helpdesk_api.domain.Ticket;
import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.domain.enums.Status;
import com.helpdesk_api.repository.CommentRepository;
import com.helpdesk_api.repository.TicketRepository;
import com.helpdesk_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;


    @Transactional
    public Comment create(Long ticketId, Long userId, Comment comment){
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found."));
        UserAccount userAccount = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        if (ticket.getStatus() == Status.CANCELED || ticket.getStatus() == Status.RESOLVED ){
             throw new RuntimeException("Only OPEN tickets can receive comments");
        }

        comment.setTicket(ticket);
        comment.setUserAccount(userAccount);
        comment.setCreatedAt(Instant.now());
        return repository.save(comment);
    }

    @Transactional(readOnly = true)
    public Comment findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found."));
    }

    @Transactional(readOnly = true)
    public List<Comment> findAllComments(){
        return repository.findAll();
    }
}
