package com.frauddetect.fraud.rules;

import com.frauddetect.events.TransactionCreatedEvent;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

@Component
public class VelocityRule implements FraudRule {

    private static final int MAX_TXNS_PER_WINDOW = 3;
    private static final Duration WINDOW = Duration.ofMinutes(1);

    private final Map<String, Deque<Instant>> cardHistory = new ConcurrentHashMap<>();

    @Override
    public boolean matches(TransactionCreatedEvent transaction) {
        String key = transaction.userId().toString();
        Deque<Instant> timestamps = cardHistory.computeIfAbsent(key, k -> new ConcurrentLinkedDeque<>());

        Instant now = Instant.now();
        timestamps.addLast(now);
        timestamps.removeIf(t -> t.isBefore(now.minus(WINDOW)));

        return timestamps.size() > MAX_TXNS_PER_WINDOW;
    }

    @Override
    public String ruleName() {
        return "VELOCITY";
    }

    @Override
    public double score() {
        return 35.0;
    }
}