package com.frauddetect.fraud.service;

import com.frauddetect.events.TransactionCreatedEvent;
import com.frauddetect.events.TransactionScoredEvent;
import com.frauddetect.fraud.client.MlScoringClient;
import com.frauddetect.fraud.dto.request.MlPredictionRequest;
import com.frauddetect.fraud.dto.response.FraudAnalysisResponse;
import com.frauddetect.fraud.dto.response.MlPredictionResponse;
import com.frauddetect.fraud.entity.FraudAnalysis;
import com.frauddetect.fraud.entity.KnownDevice;
import com.frauddetect.fraud.exception.FraudAnalysisNotFoundException;
import com.frauddetect.fraud.feature.TransactionFeatureBuilder;
import com.frauddetect.fraud.mapper.FraudAnalysisMapper;
import com.frauddetect.fraud.producer.FraudResultProducer;
import com.frauddetect.fraud.repository.FraudAnalysisRepository;
import com.frauddetect.fraud.repository.KnownDeviceRepository;
import com.frauddetect.fraud.rules.FraudDecision;
import com.frauddetect.fraud.rules.FraudRuleEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudAnalysisService implements IFraudAnalysisService {
    private final FraudRuleEngine fraudRuleEngine;
    private final FraudResultProducer fraudResultProducer;
    private final FraudAnalysisRepository fraudAnalysisRepository;
    private final FraudAnalysisMapper fraudAnalysisMapper;
    private final MlScoringClient mlScoringClient;
    private final TransactionFeatureBuilder featureBuilder;
    private final KnownDeviceService knownDeviceService;
    public FraudDecision analyze(TransactionCreatedEvent event) {
        FraudDecision decision =
                fraudRuleEngine.evaluate(event);
        MlPredictionRequest request = featureBuilder.build(event);

        MlPredictionResponse mlResponse =
                mlScoringClient.predict(request);

        double finalScore =
                (decision.riskScore() * 0.7)
                        + (mlResponse.getRiskScore() * 0.3);

        boolean fraudulent = finalScore >= 70;
        FraudDecision finalDecision =
                new FraudDecision(
                        decision.transactionId(),
                        finalScore,
                        fraudulent,
                        decision.triggeredRules()
                );
        FraudAnalysis fraudAnalysis = new FraudAnalysis();
        fraudAnalysis.setTransactionId(event.transactionId());
        fraudAnalysis.setUserId(event.userId());
        fraudAnalysis.setRiskScore(finalScore);
        fraudAnalysis.setFraudulent(fraudulent);
        fraudAnalysis.setRulesFired(decision.triggeredRules());
        fraudAnalysis.setAnalyzedAt(Instant.now());
        fraudAnalysisRepository.save(fraudAnalysis);
        fraudResultProducer.publish(event, finalDecision);
        knownDeviceService.registerDevice(
                event.userId(),
                event.deviceId()
        );
        log.info("""
                        
                        ================= FRAUD ANALYSIS =================
                        Transaction : {}
                        Risk Score  : {}
                        Fraudulent  : {}
                        Rules Fired : {}
                        ================================================
                        """,
                decision.transactionId(),
                decision.riskScore(),
                decision.fraudulent(),
                decision.triggeredRules());

        return finalDecision;
    }


    @Override
    public FraudAnalysisResponse getAnalysis(UUID transactionId) {

        FraudAnalysis fraudAnalysis = fraudAnalysisRepository
                .findByTransactionId(transactionId)
                .orElseThrow(() -> new FraudAnalysisNotFoundException(
                        "Fraud analysis not found for transaction: " + transactionId));

        return fraudAnalysisMapper.toResponse(fraudAnalysis);
    }
}
