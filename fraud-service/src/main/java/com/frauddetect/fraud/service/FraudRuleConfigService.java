package com.frauddetect.fraud.service;

import com.frauddetect.fraud.dto.request.RuleConfigRequest;
import com.frauddetect.fraud.dto.response.RuleConfigResponse;
import com.frauddetect.fraud.entity.FraudRuleConfig;
import com.frauddetect.fraud.repository.FraudRuleConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FraudRuleConfigService implements IFraudRuleConfigService {
    private final FraudRuleConfigRepository repository;

    @Override
    public Set<String> getRuleValues(String ruleName) {
        return repository.findByRuleName(ruleName)
                .stream()
                .map(FraudRuleConfig::getRuleValue)
                .collect(Collectors.toSet());
    }

    @Override
    public List<RuleConfigResponse> getAllRules() {
        return repository.findAll()
                .stream()
                .map(rule -> RuleConfigResponse.builder()
                        .ruleName(rule.getRuleName())
                        .ruleValue(rule.getRuleValue())
                        .build())
                .toList();
    }

    @Override
    public List<RuleConfigResponse> getRule(String ruleName) {
        return repository.findByRuleName(ruleName).stream().filter(r -> r.getRuleName().equals(ruleName))
                .map(rule -> RuleConfigResponse.builder()
                        .ruleName(rule.getRuleName())
                        .ruleValue(rule.getRuleValue())
                        .build())
                .toList();

    }

    @Override
    public RuleConfigResponse createRule(RuleConfigRequest request, String ruleName) {
        if (repository.findByRuleName(ruleName).stream().anyMatch(r -> r.getRuleName().equals(ruleName))) {
            throw new RuntimeException("Rule already exists");
        }
        FraudRuleConfig rule = FraudRuleConfig.builder()
                .ruleName(ruleName)
                .ruleValue(request.getRuleValue())
                .build();

        repository.save(rule);

        return RuleConfigResponse.builder()
                .ruleName(rule.getRuleName())
                .ruleValue(rule.getRuleValue())
                .build();
    }

    @Override
    public RuleConfigResponse updateRule(String ruleName, RuleConfigRequest request) {
        FraudRuleConfig rule = repository.findByRuleName(ruleName)
                .stream()
                .filter(r -> r.getRuleName().equals(ruleName))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Rule not found: " + ruleName));

        rule.setRuleValue(request.getRuleValue());
        repository.save(rule);

        return RuleConfigResponse.builder()
                .ruleName(rule.getRuleName())
                .ruleValue(rule.getRuleValue())
                .build();
    }

    @Override
    public BigDecimal getAmountThreshold(String ruleName) {

        FraudRuleConfig rule = repository.findByRuleName(ruleName).stream().filter(r -> r.getRuleName().equals(ruleName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rule not found: " + ruleName));
        return new BigDecimal(rule.getRuleValue());
    }
}
