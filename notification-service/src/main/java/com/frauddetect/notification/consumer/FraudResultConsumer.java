package com.frauddetect.notification.consumer;


import com.frauddetect.events.TransactionScoredEvent;
import com.frauddetect.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FraudResultConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "transactions.scored",
            groupId = "notification-service-group"
    )
    public void consume(TransactionScoredEvent event) {
        notificationService.process(event);

    }

}
