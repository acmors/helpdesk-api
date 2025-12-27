package com.helpdesk_api.web.controller;

import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserAccount> create(@RequestBody UserAccount userAccount){
        var createUser = userService.create(userAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }
}
