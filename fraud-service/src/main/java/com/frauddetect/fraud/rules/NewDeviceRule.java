package com.frauddetect.fraud.rules;

import com.frauddetect.events.TransactionCreatedEvent;
import com.frauddetect.fraud.repository.KnownDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewDeviceRule implements FraudRule {

    private final KnownDeviceRepository knownDeviceRepository;

    @Override
    public boolean matches(TransactionCreatedEvent transaction) {

        return !knownDeviceRepository.existsByUserIdAndDeviceId(
                transaction.userId(),
                transaction.deviceId()
        );
    }

    @Override
    public String ruleName() {
        return "NEW_DEVICE";
    }

    @Override
    public double score() {
        return 20.0;
    }
}
