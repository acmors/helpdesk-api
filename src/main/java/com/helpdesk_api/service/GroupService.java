package com.helpdesk_api.service;

import com.helpdesk_api.domain.Group;
import com.helpdesk_api.exception.ResourceNotFoundException;
import com.helpdesk_api.repository.GroupRepository;
import com.helpdesk_api.web.dto.group.GroupResponse;
import com.helpdesk_api.web.dto.group.GroupToDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository repository;

    @Transactional
    public GroupResponse create(Group group){
        Group createGroup = repository.save(group);
        return GroupToDTOMapper.toResponse(createGroup);
    }

    @Transactional(readOnly = true)
    public Group findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found"));
    }

    @Transactional(readOnly = true)
    public List<GroupResponse> findAll(){
        return repository.findAll()
                .stream()
                .map(GroupToDTOMapper::toResponse)
                .toList();
    }

    @Transactional
    public void delete(Long id){
        var group = findById(id);
        repository.delete(group);
    }
}
