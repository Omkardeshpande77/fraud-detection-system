package com.frauddetect.fraud.repository;

import com.frauddetect.fraud.entity.FraudRuleConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Repository
public interface FraudRuleConfigRepository extends JpaRepository<FraudRuleConfig, Long> {
    List<FraudRuleConfig> findByRuleName(String ruleName);


}
