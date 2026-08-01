package com.frauddetect.fraud.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {
    private long totalTransactions;

    private long fraudTransactions;

    private double fraudRate;

    private double averageRiskScore;

    private String mostTriggeredRule;

    private List<RuleStatResponse> topRules;
}
