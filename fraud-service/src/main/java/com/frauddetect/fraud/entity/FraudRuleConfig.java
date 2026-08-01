package com.frauddetect.fraud.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fraud_rule_config")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudRuleConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleName;

    @Column(nullable = false)
    private String ruleValue;
}
