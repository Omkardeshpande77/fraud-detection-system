package com.frauddetect.transaction.kafka.producer;

import com.frauddetect.events.TransactionCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionProducer {
    private static final Logger log =
            LoggerFactory.getLogger(TransactionProducer.class);

    private final KafkaTemplate<String, TransactionCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "transactions.created";

    public TransactionProducer(KafkaTemplate<String, TransactionCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(TransactionCreatedEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event.transactionId().toString(),
                event
        );

        log.info("Published transaction {} to Kafka",
                event.transactionId());
    }
}
