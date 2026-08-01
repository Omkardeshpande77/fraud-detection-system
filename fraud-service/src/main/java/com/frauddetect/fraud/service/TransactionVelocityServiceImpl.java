package com.frauddetect.fraud.service;

import com.frauddetect.fraud.repository.TransactionSnapshotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionVelocityServiceImpl implements ITransactionVelocityService {

    private final TransactionSnapshotRepository transactionSnapshotRepository;

    @Override
    public int getTransactionsLastHour(UUID userId) {
        // Implementation for getting transaction velocity
        Instant oneHourAgo =
                Instant.now().minus(1, ChronoUnit.HOURS);

        return (int) transactionSnapshotRepository.countByUserIdAndCreatedAtAfter(
                userId,
                oneHourAgo
        );
    }
}
