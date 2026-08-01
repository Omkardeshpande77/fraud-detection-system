package com.frauddetect.fraud.service;

import com.frauddetect.fraud.dto.request.RuleConfigRequest;
import com.frauddetect.fraud.dto.response.RuleConfigResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface IFraudRuleConfigService {

    BigDecimal getAmountThreshold(String ruleName);

    Set<String> getRuleValues(String ruleName);

    List<RuleConfigResponse> getAllRules();

    List<RuleConfigResponse>getRule(String ruleKey);

    RuleConfigResponse createRule(RuleConfigRequest request, String ruleKey);

    RuleConfigResponse updateRule(String ruleKey, RuleConfigRequest request);
}
