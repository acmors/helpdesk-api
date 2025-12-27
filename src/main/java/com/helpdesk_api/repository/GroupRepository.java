package com.helpdesk_api.repository;

import com.helpdesk_api.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
