package com.frauddetect.fraud.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FraudAnalysisResponse {
    private UUID transactionId;
    private Double riskScore;
    private Boolean fraudulent;
    private List<String> rulesTriggered;
}
