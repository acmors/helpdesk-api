package com.helpdesk_api.service;

import com.helpdesk_api.domain.Comment;
import com.helpdesk_api.domain.Ticket;
import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.domain.enums.Status;
import com.helpdesk_api.exception.ResourceNotFoundException;
import com.helpdesk_api.repository.CommentRepository;
import com.helpdesk_api.repository.TicketRepository;
import com.helpdesk_api.repository.UserRepository;
import com.helpdesk_api.web.dto.comment.CommentResponse;
import com.helpdesk_api.web.dto.comment.CommentToDTOMapper;
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
    public CommentResponse create(Long ticketId, Long userId, Comment comment){
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found."));
        UserAccount userAccount = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        if (ticket.getStatus() == Status.CANCELED || ticket.getStatus() == Status.RESOLVED ){
             throw new RuntimeException("Only OPEN tickets can receive comments");
        }

        comment.setTicket(ticket);
        comment.setUserAccount(userAccount);
        comment.setCreatedAt(Instant.now());
        Comment createComment = repository.save(comment);

        return CommentToDTOMapper.toResponse(createComment);
    }

    @Transactional(readOnly = true)
    public Comment findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found."));
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> findAllComments(){
        return repository.findAll()
                .stream()
                .map(CommentToDTOMapper::toResponse)
                .toList();
    }
}
