package com.frauddetect.fraud.repository;

import com.frauddetect.fraud.entity.TransactionSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionSnapshotRepository extends JpaRepository<TransactionSnapshot, UUID> {
    long countByUserIdAndCreatedAtAfter(
            UUID userId,
            Instant createdAt
    );

    boolean existsByTransactionId(UUID transactionId);
}
