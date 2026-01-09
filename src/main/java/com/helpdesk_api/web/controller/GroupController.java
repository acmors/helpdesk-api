package com.helpdesk_api.web.controller;

import com.helpdesk_api.domain.Group;
import com.helpdesk_api.service.GroupService;
import com.helpdesk_api.web.dto.group.GroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    private final GroupService service;

    @PostMapping
    public ResponseEntity<GroupResponse> create(@RequestBody Group group){
        var createGroup = service.create(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(createGroup);
    }

}
