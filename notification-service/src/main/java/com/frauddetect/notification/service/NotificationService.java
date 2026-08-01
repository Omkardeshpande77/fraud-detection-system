package com.frauddetect.notification.service;

import com.frauddetect.events.TransactionScoredEvent;
import com.frauddetect.notification.dto.response.NotificationResponse;
import com.frauddetect.notification.entity.Notification;
import com.frauddetect.notification.mapper.NotificationMapper;
import com.frauddetect.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void process(TransactionScoredEvent event) {
        Notification notification = new Notification();

        notification.setTransactionId(event.transactionId());
        notification.setUserId(event.userId());

        notification.setChannel("EMAIL");

        notification.setMessage(
                event.fraudulent()
                        ? "⚠️ Fraudulent transaction detected."
                        : "✅ Transaction processed successfully."
        );

        notification.setDelivered(true);
        notification.setCreatedAt(Instant.now());

        notificationRepository.save(notification);

        log.info("Notification saved for transaction {}", event.transactionId());
    }

    public List<NotificationResponse> getByTransactionId(UUID transactionId) {

        return notificationRepository.findByTransactionId(transactionId)
                .stream()
                .map(NotificationMapper::toResponse)
                .toList();
    }
}
