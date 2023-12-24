package com.poc.security.repository;

import com.poc.security.entity.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEvent, Long> {
    Optional<UserEvent> findByUserNameIgnoringCase(String userName);
}
