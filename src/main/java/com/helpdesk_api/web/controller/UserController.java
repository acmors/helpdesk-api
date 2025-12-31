package com.helpdesk_api.web.controller;

import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.service.UserService;
import com.helpdesk_api.web.dto.user.UserCreate;
import com.helpdesk_api.web.dto.user.UserResponse;
import com.helpdesk_api.web.dto.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserCreate dto){
        UserAccount userAccount =  userService.create(UserMapper.toUser(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDTO(userAccount));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listAll(){
        List<UserAccount> users =  userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toListDTO(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable("id") Long id){
        UserAccount userAccount = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDTO(userAccount));
    }
}
