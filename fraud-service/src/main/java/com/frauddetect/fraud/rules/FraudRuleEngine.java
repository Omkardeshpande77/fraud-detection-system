package com.frauddetect.fraud.rules;

import com.frauddetect.events.TransactionCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FraudRuleEngine {

    private static final double FRAUD_THRESHOLD = 70.0;

    private final List<FraudRule> rules;

    public FraudRuleEngine(List<FraudRule> rules) {
        this.rules = rules;
    }

    public FraudDecision evaluate(TransactionCreatedEvent transaction) {

        double riskScore = 0.0;
        List<String> triggeredRules = new ArrayList<>();

        log.info("Evaluating {} fraud rules...", rules.size());

        for (FraudRule rule : rules) {

            boolean matched = rule.matches(transaction);

            log.info("Rule {} -> {}", rule.ruleName(), matched);

            if (matched) {
                riskScore += rule.score();
                triggeredRules.add(rule.ruleName());
            }
        }

        return new FraudDecision(
                transaction.transactionId(),
                riskScore,
                riskScore >= FRAUD_THRESHOLD,
                triggeredRules
        );
    }
}