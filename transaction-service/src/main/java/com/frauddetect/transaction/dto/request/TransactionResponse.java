package com.frauddetect.transaction.dto.request;

import com.fraudDetect.enums.Currency;
import com.fraudDetect.enums.PaymentMethod;
import com.fraudDetect.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionResponse(
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

        Instant createdAt
) {
}
