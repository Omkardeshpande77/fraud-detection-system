package com.frauddetect.notification.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record NotificationResponse(
        UUID transactionId,
        UUID userId,
        String channel,
        String message,
        boolean delivered,
        Instant createdAt
) {
}
