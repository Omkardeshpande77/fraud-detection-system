package com.frauddetect.notification.service;

import com.frauddetect.events.TransactionScoredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    public void sendAlert(TransactionScoredEvent event) {
        log.warn("""
                        
                        ================ FRAUD ALERT =================
                        
                        Transaction : {}
                        Risk Score  : {}
                        Rules Fired : {}
                        
                        ALERT SENT TO CUSTOMER
                        
                        ==============================================
                        
                        """,

                event.transactionId(),

                event.riskScore(),

                event.triggeredRules());

    }
}
