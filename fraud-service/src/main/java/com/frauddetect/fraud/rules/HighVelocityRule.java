package com.frauddetect.fraud.rules;

import com.frauddetect.events.TransactionCreatedEvent;
import com.frauddetect.fraud.repository.FraudAnalysisRepository;
import com.frauddetect.fraud.repository.TransactionSnapshotRepository;
import com.frauddetect.fraud.service.FraudRuleConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class HighVelocityRule implements FraudRule {

    private final FraudAnalysisRepository fraudAnalysisRepository;

    @Override
    public boolean matches(TransactionCreatedEvent transaction) {
        Instant oneMinuteAgo = Instant.now().minusSeconds(60);

        long count = fraudAnalysisRepository
                .countByUserIdAndAnalyzedAtAfter(
                        transaction.userId(),
                        oneMinuteAgo);

        return count >= 5;
    }

    @Override
    public String ruleName() {
        return "HIGH_VELOCITY";
    }

    @Override
    public double score() {
        return 20.0;
    }
}


