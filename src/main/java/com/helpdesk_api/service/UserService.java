package com.helpdesk_api.service;

import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.domain.enums.Role;
import com.helpdesk_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserAccount create(UserAccount userAccount){
        userAccount.setRole(Role.USER);
        return userRepository.save(userAccount);
    }

    @Transactional(readOnly = true)
    public UserAccount findByUserResponseId(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not Found."));
    }

    @Transactional(readOnly = true)
    public UserAccount findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not Found."));
    }

    @Transactional(readOnly = true)
    public List<UserAccount> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public void delete(Long id){
        var user = findById(id);
        userRepository.delete(user);
    }
}
