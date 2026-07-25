package com.frauddetect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionDTO(
        @NotBlank
        String transactionId,
        @NotNull
        Long userId,
        @Positive
        BigDecimal amount,
        @NotBlank
        String currency,
        @NotBlank
        String merchant,
        @NotBlank
        String country,
        @NotNull
        Instant timestamp
) {
}
