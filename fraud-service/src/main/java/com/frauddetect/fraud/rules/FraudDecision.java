package com.frauddetect.fraud.rules;

import java.util.List;
import java.util.UUID;

public record FraudDecision(
        UUID transactionId,

        double riskScore,

        boolean fraudulent,

        List<String> triggeredRules
) {
}
