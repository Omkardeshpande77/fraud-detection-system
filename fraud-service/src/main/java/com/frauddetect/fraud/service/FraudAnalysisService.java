package com.frauddetect.fraud.service;

import com.frauddetect.events.TransactionCreatedEvent;
import com.frauddetect.fraud.producer.FraudResultProducer;
import com.frauddetect.fraud.rules.FraudDecision;
import com.frauddetect.fraud.rules.FraudRuleEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FraudAnalysisService {
    private final FraudRuleEngine fraudRuleEngine;
    private final FraudResultProducer fraudResultProducer;


    public FraudAnalysisService(FraudRuleEngine fraudRuleEngine, FraudResultProducer fraudResultProducer) {
        this.fraudRuleEngine = fraudRuleEngine;
        this.fraudResultProducer = fraudResultProducer;
    }

    public FraudDecision analyze(TransactionCreatedEvent event) {
        FraudDecision decision =
                fraudRuleEngine.evaluate(event);

        fraudResultProducer.publish(event, decision);

        log.info("""
                        
                        ================= FRAUD ANALYSIS =================
                        Transaction : {}
                        Risk Score  : {}
                        Fraudulent  : {}
                        Rules Fired : {}
                        ================================================
                        """,
                decision.transactionId(),
                decision.riskScore(),
                decision.fraudulent(),
                decision.triggeredRules());

        return decision;
    }
}
