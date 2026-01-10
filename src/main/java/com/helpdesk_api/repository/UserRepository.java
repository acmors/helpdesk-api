package com.helpdesk_api.repository;

import com.helpdesk_api.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

    boolean existsByEmail(String email);


    Optional<UserAccount> findByEmail(String email);
}
