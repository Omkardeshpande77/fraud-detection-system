package com.fraudDetect.dto;

import com.fraudDetect.enums.RiskLevel;

public record FraudResultDTO(
        String transactionId,
        double riskScore,
        RiskLevel riskLevel,
        boolean fraudulent
) {
}
