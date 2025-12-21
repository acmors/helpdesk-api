package com.helpdesk_api.service;

import com.helpdesk_api.domain.User;
import com.helpdesk_api.domain.enums.Role;
import com.helpdesk_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user){
        user.setRole(Role.USER);

        return userRepository.save(user);
    }
}
