package com.frauddetect.fraud.rules;

import com.frauddetect.events.TransactionCreatedEvent;
import com.frauddetect.fraud.entity.KnownDevice;
import com.frauddetect.fraud.entity.RuleExecutionHistory;
import com.frauddetect.fraud.repository.KnownDeviceRepository;
import com.frauddetect.fraud.repository.RuleExecutionHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FraudRuleEngine {

    private static final double FRAUD_THRESHOLD = 70.0;

    private final List<FraudRule> rules;
    private final RuleExecutionHistoryRepository historyRepository;
    private final KnownDeviceRepository knownDeviceRepository;

    public FraudDecision evaluate(TransactionCreatedEvent transaction) {

        double riskScore = 0.0;
        List<String> triggeredRules = new ArrayList<>();

        log.info("Evaluating {} fraud rules...", rules.size());

        for (FraudRule rule : rules) {

            boolean matched = rule.matches(transaction);
            historyRepository.save(
                    RuleExecutionHistory.builder()
                            .transactionId(transaction.transactionId())
                            .ruleName(rule.ruleName())
                            .fired(matched)
                            .scoreContribution(matched ? rule.score() : 0.0)
                            .build()
            );
            log.info("Rule {} -> {}", rule.ruleName(), matched);

            if (matched) {
                riskScore += rule.score();
                triggeredRules.add(rule.ruleName());
            }
        }
        if (!knownDeviceRepository.existsByUserIdAndDeviceId(
                transaction.userId(),
                transaction.deviceId())) {

            knownDeviceRepository.save(
                    KnownDevice.builder()
                            .userId(transaction.userId())
                            .deviceId(transaction.deviceId())
                            .firstSeen(transaction.createdAt())
                            .build()
            );
        }
        return new FraudDecision(
                transaction.transactionId(),
                riskScore,
                riskScore >= FRAUD_THRESHOLD,
                triggeredRules
        );
    }
}