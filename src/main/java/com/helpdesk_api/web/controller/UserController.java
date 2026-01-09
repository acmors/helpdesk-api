package com.helpdesk_api.web.controller;

import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.service.UserService;
import com.helpdesk_api.web.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserAccount user){
        service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listAll(){
        var list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
