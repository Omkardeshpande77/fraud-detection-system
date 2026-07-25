package com.frauddetect.events;

import com.frauddetect.enums.Currency;
import com.frauddetect.enums.PaymentMethod;
import com.frauddetect.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionCreatedEvent(
        UUID transactionId,

        UUID userId,

        UUID merchantId,

        BigDecimal amount,

        Currency currency,

        String merchantName,

        String merchantCategory,

        String country,

        PaymentMethod paymentMethod,

        TransactionStatus status,
        double[] features,
        Instant createdAt

) {
}
