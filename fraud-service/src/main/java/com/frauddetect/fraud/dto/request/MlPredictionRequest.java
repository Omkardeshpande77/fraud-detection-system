package com.frauddetect.fraud.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MlPredictionRequest {
    private double amount;

    private String country;

    private String merchantCategory;

    private String paymentMethod;
    @JsonProperty("isNewDevice")
    private boolean isNewDevice;

    private int transactionsLastHour;
}
