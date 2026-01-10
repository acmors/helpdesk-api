package com.helpdesk_api.service;

import com.helpdesk_api.domain.Ticket;
import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.domain.enums.Role;
import com.helpdesk_api.exception.ResourceAlreadyExistsException;
import com.helpdesk_api.exception.ResourceNotFoundException;
import com.helpdesk_api.exception.UsernameUniqueViolationException;
import com.helpdesk_api.repository.UserRepository;
import com.helpdesk_api.web.dto.user.UserResponse;
import com.helpdesk_api.web.dto.user.UserToDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse create(UserAccount userAccount){

        try{
            userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
            userAccount.setRole(Role.USER);
            UserAccount create = userRepository.save(userAccount);
            return UserToDTOMapper.toResponse(create);
        }catch (DataIntegrityViolationException ex){
            throw new UsernameUniqueViolationException(String.format("Usernmae {%s} already exists.", userAccount.getEmail()));
        }
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

    @Transactional(readOnly = true)
    public UserAccount findEntityByUsername(String username){
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username not found."));
    }

    @Transactional(readOnly = true)
    public UserResponse findByUsername(String username) {
        var findUsername = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username not found."));

        return UserToDTOMapper.toResponse(findUsername);
    }
}
