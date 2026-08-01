package com.frauddetect.fraud.rules;

import com.frauddetect.events.TransactionCreatedEvent;
import com.frauddetect.fraud.service.FraudRuleConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class HighAmountRule implements FraudRule {

    private final FraudRuleConfigService configService;

    @Override
    public boolean matches(TransactionCreatedEvent transaction) {
        return transaction.amount().compareTo(configService.getAmountThreshold("HIGH_AMOUNT")) >= 0;
    }

    @Override
    public String ruleName() {
        return "HIGH_AMOUNT";
    }

    @Override
    public double score() {
        return 50.0;
    }
}