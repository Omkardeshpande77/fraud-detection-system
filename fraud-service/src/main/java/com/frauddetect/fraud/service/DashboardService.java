package com.frauddetect.fraud.service;

import com.frauddetect.fraud.dto.response.DashboardResponse;
import com.frauddetect.fraud.dto.response.RuleStatResponse;
import com.frauddetect.fraud.repository.FraudAnalysisRepository;
import com.frauddetect.fraud.repository.RuleExecutionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService implements IDashboardService {
    private final FraudAnalysisRepository fraudAnalysisRepository;
    private final RuleExecutionHistoryRepository historyRepository;

    @Override
    public DashboardResponse getDashboard() {
        long totalTransactions = fraudAnalysisRepository.count();

        long fraudTransactions =
                fraudAnalysisRepository.countByFraudulentTrue();

        Double averageRisk =
                fraudAnalysisRepository.averageRiskScore();

        double fraudRate = totalTransactions == 0
                ? 0
                : ((double) fraudTransactions / totalTransactions) * 100;


        List<Object[]> stats = historyRepository.getRuleStatistics();

        List<RuleStatResponse> topRules = stats.stream()
                .map(row -> RuleStatResponse.builder()
                        .ruleName((String) row[0])
                        .count((Long) row[1])
                        .build())
                .toList();

        String mostTriggeredRule =
                topRules.isEmpty()
                        ? "N/A"
                        : topRules.getFirst().getRuleName();

        return DashboardResponse.builder()
                .totalTransactions(totalTransactions)
                .fraudTransactions(fraudTransactions)
                .fraudRate(fraudRate)
                .averageRiskScore(averageRisk == null ? 0 : averageRisk)
                .mostTriggeredRule(mostTriggeredRule)
                .topRules(topRules)
                .build();
    }
}
