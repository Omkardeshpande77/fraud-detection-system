package com.frauddetect.fraud.repository;

import com.frauddetect.fraud.entity.BlacklistedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlacklistedUserRepository extends JpaRepository<BlacklistedUser, UUID> {
    boolean existsByUserId(UUID userId);
}
