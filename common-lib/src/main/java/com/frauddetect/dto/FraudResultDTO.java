package com.frauddetect.dto;

import com.frauddetect.enums.RiskLevel;

public record FraudResultDTO(
        String transactionId,
        double riskScore,
        RiskLevel riskLevel,
        boolean fraudulent
) {
}
