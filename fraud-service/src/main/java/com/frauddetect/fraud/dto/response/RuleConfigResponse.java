package com.frauddetect.fraud.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RuleConfigResponse {

    private String ruleName;

    private String ruleValue;
}
