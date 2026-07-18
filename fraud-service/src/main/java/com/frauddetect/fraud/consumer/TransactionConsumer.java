package com.frauddetect.fraud.consumer;

import com.fraudDetect.events.TransactionCreatedEvent;
import com.frauddetect.fraud.service.FraudAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransactionConsumer {


    private final FraudAnalysisService fraudAnalysisService;

    public TransactionConsumer(FraudAnalysisService fraudAnalysisService) {
        this.fraudAnalysisService = fraudAnalysisService;
    }

    @KafkaListener(
            topics = "transactions.created",
            groupId = "fraud-service-group"
    )
    public void consume(TransactionCreatedEvent event) {

        log.info("Received {}", event);
        fraudAnalysisService.analyze(event);

    }

}
