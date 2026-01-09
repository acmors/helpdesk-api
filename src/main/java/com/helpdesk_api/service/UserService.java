package com.helpdesk_api.service;

import com.helpdesk_api.domain.Ticket;
import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.domain.enums.Role;
import com.helpdesk_api.exception.ResourceAlreadyExistsException;
import com.helpdesk_api.exception.ResourceNotFoundException;
import com.helpdesk_api.repository.UserRepository;
import com.helpdesk_api.web.dto.user.UserResponse;
import com.helpdesk_api.web.dto.user.UserToDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse create(UserAccount userAccount){
        if (userRepository.existsByEmail(userAccount.getEmail())){
            throw new ResourceAlreadyExistsException("Already exists a user with this Email.");
        }

        userAccount.setRole(Role.USER);
        UserAccount create = userRepository.save(userAccount);
        return UserToDTOMapper.toResponse(create);
    }

    @Transactional(readOnly = true)
    public UserAccount findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not Found."));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll(){
        return userRepository.findAll()
                .stream()
                .map(UserToDTOMapper::toResponse)
                .toList();
    }

    @Transactional
    public void delete(Long id){
        var user = findById(id);
        userRepository.delete(user);
    }
}
