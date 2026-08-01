package com.frauddetect.fraud.rules;

import com.frauddetect.events.TransactionCreatedEvent;

public interface  FraudRule {
    boolean matches(TransactionCreatedEvent transaction);

    String ruleName();

    double score();
}
