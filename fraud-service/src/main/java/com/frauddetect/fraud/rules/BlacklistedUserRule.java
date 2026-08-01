package com.frauddetect.fraud.rules;

import com.frauddetect.events.TransactionCreatedEvent;
import com.frauddetect.fraud.repository.BlacklistedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlacklistedUserRule implements FraudRule{

    private final BlacklistedUserRepository repository;

    @Override
    public boolean matches(TransactionCreatedEvent transaction) {
        return repository.existsByUserId(transaction.userId());
    }

    @Override
    public String ruleName() {
        return "BLACKLISTED_USER";
    }

    @Override
    public double score() {
        return 100.0;

    }
}
