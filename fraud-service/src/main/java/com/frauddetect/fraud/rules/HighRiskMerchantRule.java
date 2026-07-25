package com.frauddetect.fraud.rules;

import com.frauddetect.events.TransactionCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class HighRiskMerchantRule implements FraudRule{

    private static final Set<String> HIGH_RISK = Set.of(
            "CRYPTO","GAMBLING","LUXURY"
    );

    @Override
    public boolean matches(TransactionCreatedEvent transaction) {
        return HIGH_RISK.contains(transaction.merchantCategory().toUpperCase());
    }

    @Override
    public String ruleName() {
        return "HIGH_RISK_MERCHANT";
    }

    @Override
    public double score() {
        return 40;
    }
}
