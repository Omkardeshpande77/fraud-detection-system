package com.frauddetect.fraud.mapper;

import com.frauddetect.fraud.dto.response.FraudAnalysisResponse;
import com.frauddetect.fraud.entity.FraudAnalysis;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class FraudAnalysisMapper {

    public FraudAnalysisResponse toResponse(FraudAnalysis fraudAnalysis) {

        return FraudAnalysisResponse.builder()
                .transactionId(fraudAnalysis.getTransactionId())
                .riskScore(fraudAnalysis.getRiskScore())
                .fraudulent(fraudAnalysis.getFraudulent())
                .rulesTriggered(fraudAnalysis.getRulesFired())
                .build();
    }
}
