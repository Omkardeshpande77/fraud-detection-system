package com.frauddetect.fraud.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RuleConfigRequest {
    @NotBlank
    private String ruleValue;

}
