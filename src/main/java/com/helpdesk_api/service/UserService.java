package com.helpdesk_api.service;

import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.domain.enums.Role;
import com.helpdesk_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserAccount create(UserAccount userAccount){
        userAccount.setRole(Role.USER);

        return userRepository.save(userAccount);
    }
}
