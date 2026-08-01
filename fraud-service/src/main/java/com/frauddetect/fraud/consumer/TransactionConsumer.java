package com.frauddetect.fraud.consumer;

import com.frauddetect.events.TransactionCreatedEvent;
import com.frauddetect.fraud.entity.TransactionSnapshot;
import com.frauddetect.fraud.repository.TransactionSnapshotRepository;
import com.frauddetect.fraud.service.FraudAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionConsumer {


    private final FraudAnalysisService fraudAnalysisService;
    private final TransactionSnapshotRepository transactionSnapshotRepository;

    @KafkaListener(
            topics = "transactions.created",
            groupId = "fraud-service-group"
    )
    public void consume(TransactionCreatedEvent event) {

        log.info("Received {}", event);

        if (!transactionSnapshotRepository.existsByTransactionId(event.transactionId())) {

            transactionSnapshotRepository.save(
                    TransactionSnapshot.builder()
                            .transactionId(event.transactionId())
                            .userId(event.userId())
                            .amount(event.amount())
                            .createdAt(event.createdAt())
                            .build()
            );
        }
        fraudAnalysisService.analyze(event);

    }

}
