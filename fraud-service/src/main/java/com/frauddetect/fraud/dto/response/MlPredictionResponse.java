package com.frauddetect.fraud.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MlPredictionResponse {
    private double riskScore;

    private double fraudProbability;

    private boolean fraudulent;
}
