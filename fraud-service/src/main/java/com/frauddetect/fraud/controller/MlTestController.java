package com.frauddetect.fraud.controller;

import com.frauddetect.fraud.client.MlScoringClient;
import com.frauddetect.fraud.dto.request.MlPredictionRequest;
import com.frauddetect.fraud.dto.response.MlPredictionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ml")
@RequiredArgsConstructor
public class MlTestController {

    private final MlScoringClient client;

    @PostMapping("/predict")
    public MlPredictionResponse predict(
            @RequestBody MlPredictionRequest request) {

        return client.predict(request);
    }
}
