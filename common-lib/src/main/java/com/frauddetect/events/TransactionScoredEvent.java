package com.frauddetect.events;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record TransactionScoredEvent(
        UUID transactionId,

        UUID userId,

        double riskScore,

        boolean fraudulent,

        List<String> triggeredRules,

        Instant processedAt

) {
}
