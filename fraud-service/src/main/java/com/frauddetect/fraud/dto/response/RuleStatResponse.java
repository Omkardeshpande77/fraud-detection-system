package com.frauddetect.fraud.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RuleStatResponse {
    private String ruleName;

    private long count;
}
