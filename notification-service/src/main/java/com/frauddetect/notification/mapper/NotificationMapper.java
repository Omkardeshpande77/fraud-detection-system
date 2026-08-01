package com.frauddetect.notification.mapper;

import com.frauddetect.notification.dto.response.NotificationResponse;
import com.frauddetect.notification.entity.Notification;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotificationMapper {
    public static NotificationResponse toResponse(Notification notification) {

        return NotificationResponse.builder()
                .transactionId(notification.getTransactionId())
                .userId(notification.getUserId())
                .channel(notification.getChannel())
                .message(notification.getMessage())
                .delivered(notification.isDelivered())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}
