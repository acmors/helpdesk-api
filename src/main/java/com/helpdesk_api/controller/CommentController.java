package com.helpdesk_api.controller;

import com.helpdesk_api.domain.Comment;
import com.helpdesk_api.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping("/tickets/{ticketId}/comments")
    public ResponseEntity<Comment> create(@PathVariable Long ticketId,
                                          @RequestParam Long userId,
                                          @RequestBody Comment comment){
        var addComment = service.create(ticketId, userId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(addComment);
    }
}
