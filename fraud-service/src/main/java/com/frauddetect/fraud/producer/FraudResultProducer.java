package com.frauddetect.fraud.producer;

import com.fraudDetect.events.TransactionCreatedEvent;
import com.fraudDetect.events.TransactionScoredEvent;
import com.frauddetect.fraud.rules.FraudDecision;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudResultProducer {

    private final KafkaTemplate<String, TransactionScoredEvent> kafkaTemplate;

    public void publish(
            TransactionCreatedEvent transaction,
            FraudDecision decision
    ) {

        TransactionScoredEvent event =
                new TransactionScoredEvent(

                        transaction.transactionId(),

                        transaction.userId(),

                        decision.riskScore(),

                        decision.fraudulent(),

                        decision.triggeredRules(),

                        Instant.now()

                );

        kafkaTemplate.send(
                "transactions.scored",
                transaction.transactionId().toString(),
                event
        );

        log.info(
                "Published Fraud Result for Transaction {}",
                transaction.transactionId()
        );
    }
}
