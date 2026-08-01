package com.frauddetect.fraud.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fraud_analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID transactionId;
    @Column(nullable =false)
    private UUID userId;
    private Double riskScore;
    private Boolean fraudulent;
    @Convert(converter = StringListJsonConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> rulesFired;
    @Column(nullable = false)
    private Instant analyzedAt;
}
