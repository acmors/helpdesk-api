package com.helpdesk_api.jwt;

import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = service.findEntityByUsername(username);
        return new JwtUserDetails(userAccount);
    }

    public JwtToken getTokenAuthenticated(String username){
        UserAccount user = service.findEntityByUsername(username);
        return JwtUtils.createToken(user.getEmail(), user.getRole().name());
    }
}
