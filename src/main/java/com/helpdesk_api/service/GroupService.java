package com.helpdesk_api.service;

import com.helpdesk_api.domain.Group;
import com.helpdesk_api.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository repository;

    @Transactional
    public Group create(Group group){
        return repository.save(group);
    }

    @Transactional(readOnly = true)
    public Group findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    @Transactional(readOnly = true)
    public List<Group> findAll(){
        return repository.findAll();
    }

    @Transactional
    public void delete(Long id){
        var group = findById(id);
        repository.delete(group);
    }
}
