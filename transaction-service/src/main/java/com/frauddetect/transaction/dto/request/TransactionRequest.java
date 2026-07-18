package com.frauddetect.transaction.dto.request;

import com.fraudDetect.enums.Currency;
import com.fraudDetect.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequest(
        @NotNull
        UUID userId,
        @NotNull
        UUID merchantId,
        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal amount,
        @NotNull
        Currency currency,
        @NotBlank
        @Size(max = 100)
        String merchantName,
        @NotBlank
        @Size(max = 100)
        String merchantCategory,
        @NotBlank
        @Size(min = 2, max = 50)
        String country,
        @NotNull
        PaymentMethod paymentMethod
) {
}
