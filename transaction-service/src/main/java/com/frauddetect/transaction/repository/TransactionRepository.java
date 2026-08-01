package com.frauddetect.transaction.repository;

import com.frauddetect.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findByTransactionId(UUID transactionId);

    long countByUserIdAndCreatedAtAfter(UUID userId, Instant timestamp);
}
