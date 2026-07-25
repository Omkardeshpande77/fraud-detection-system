package com.frauddetect.fraud.rules;

import com.frauddetect.events.TransactionCreatedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HighAmountRule implements FraudRule{
    private static final BigDecimal LIMIT = BigDecimal.valueOf(100000);

    @Override
    public boolean matches(TransactionCreatedEvent transaction) {

        return transaction.amount().compareTo(LIMIT) >= 0;
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
