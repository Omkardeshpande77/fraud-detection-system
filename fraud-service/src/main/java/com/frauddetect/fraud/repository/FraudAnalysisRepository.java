package com.frauddetect.fraud.repository;

import com.frauddetect.fraud.entity.FraudAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FraudAnalysisRepository extends JpaRepository<FraudAnalysis, UUID> {
    Optional<FraudAnalysis> findByTransactionId(UUID transactionId);

    long countByUserIdAndAnalyzedAtAfter(
            UUID userId,
            Instant after
    );

    long countByFraudulentTrue();

    @Query("""
SELECT AVG(f.riskScore)
FROM FraudAnalysis f
""")
    Double averageRiskScore();
}
