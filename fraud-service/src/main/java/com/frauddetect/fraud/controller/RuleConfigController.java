package com.frauddetect.fraud.controller;

import com.frauddetect.fraud.dto.request.RuleConfigRequest;
import com.frauddetect.fraud.dto.response.RuleConfigResponse;
import com.frauddetect.fraud.service.FraudRuleConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rules")
@RequiredArgsConstructor
public class RuleConfigController {
    private final FraudRuleConfigService ruleConfigService;

    @GetMapping
    public List<RuleConfigResponse> getAllRules() {
        return ruleConfigService.getAllRules();
    }

    @GetMapping("/{ruleName}")
    public List<RuleConfigResponse> getRule(
            @PathVariable String ruleName) {

        return ruleConfigService.getRule(ruleName);
    }

    @PutMapping("/{ruleName}")
    public RuleConfigResponse updateRule(
            @PathVariable String ruleName,
            @Valid @RequestBody RuleConfigRequest request) {

        return ruleConfigService.updateRule(ruleName, request);
    }

    @PostMapping("/{ruleName}")
    public RuleConfigResponse createRule(
            @PathVariable String ruleName,
            @Valid @RequestBody RuleConfigRequest request) {

        return ruleConfigService.createRule(request, ruleName);
    }
}
