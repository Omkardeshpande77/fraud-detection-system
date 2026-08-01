package com.frauddetect.fraud.rules;

import com.frauddetect.events.TransactionCreatedEvent;
import com.frauddetect.fraud.service.FraudRuleConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class HighRiskCountryRule implements FraudRule {

    private final FraudRuleConfigService configService;

    @Override
    public boolean matches(TransactionCreatedEvent transaction) {

        return configService
                .getRuleValues("HIGH_RISK_COUNTRY")
                .contains(transaction.country());
    }

    @Override
    public String ruleName() {
        return "HIGH_RISK_COUNTRY";
    }

    @Override
    public double score() {
        return 30.0;
    }
}
