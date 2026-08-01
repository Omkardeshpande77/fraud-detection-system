package com.frauddetect.notification.repository;

import com.frauddetect.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    List<Notification> findByTransactionId(UUID transactionId);

    List<Notification> findByUserId(UUID userId);
}
