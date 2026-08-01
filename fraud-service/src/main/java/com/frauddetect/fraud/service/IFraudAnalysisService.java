package com.frauddetect.fraud.service;

import com.frauddetect.fraud.dto.response.FraudAnalysisResponse;

import java.util.UUID;

public interface IFraudAnalysisService {
    FraudAnalysisResponse getAnalysis(UUID transactionId);
}
