package com.frauddetect.fraud.rules;

import com.frauddetect.events.TransactionCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class ForeignCountryRule implements FraudRule{



    @Override
    public boolean matches(TransactionCreatedEvent transaction) {
        return !"India".equalsIgnoreCase(transaction.country());
    }

    @Override
    public String ruleName() {
        return "FOREIGN_COUNTRY";
    }

    @Override
    public double score() {
        return 30.0;
    }
}
