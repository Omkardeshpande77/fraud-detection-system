package com.frauddetect.fraud.client;

import com.frauddetect.fraud.config.MlServiceProperties;
import com.frauddetect.fraud.dto.request.MlPredictionRequest;
import com.frauddetect.fraud.dto.response.MlPredictionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class MlScoringClient {
    private final MlServiceProperties properties;

    private final RestClient restClient = RestClient.create();

    public MlPredictionResponse predict(MlPredictionRequest request) {

        return restClient.post()
                .uri(properties.getUrl() + "/predict")
                .body(request)
                .retrieve()
                .body(MlPredictionResponse.class);
    }
}
