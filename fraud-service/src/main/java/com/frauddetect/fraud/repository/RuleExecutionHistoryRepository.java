package com.frauddetect.fraud.repository;

import com.frauddetect.fraud.entity.RuleExecutionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RuleExecutionHistoryRepository extends JpaRepository<RuleExecutionHistory, UUID> {
    List<RuleExecutionHistory> findByTransactionId(UUID transactionId);

    @Query("""
SELECT r.ruleName, COUNT(r)
FROM RuleExecutionHistory r
WHERE r.fired = true
GROUP BY r.ruleName
ORDER BY COUNT(r) DESC
""")
    List<Object[]> getRuleStatistics();
}
