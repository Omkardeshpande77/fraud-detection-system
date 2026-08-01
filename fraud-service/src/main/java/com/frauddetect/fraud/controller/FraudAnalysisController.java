package com.frauddetect.fraud.controller;



import com.frauddetect.fraud.dto.response.FraudAnalysisResponse;
import com.frauddetect.fraud.service.FraudAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/fraud")
@RequiredArgsConstructor
public class FraudAnalysisController {
    private final FraudAnalysisService fraudAnalysisService;

    @GetMapping("/{transactionId}")
     public FraudAnalysisResponse getFraudAnalysis(@PathVariable UUID transactionId){
         return fraudAnalysisService.getAnalysis(transactionId);
     }
}
