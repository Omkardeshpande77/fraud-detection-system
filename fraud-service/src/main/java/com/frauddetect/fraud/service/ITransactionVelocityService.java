package com.frauddetect.fraud.service;

import java.util.UUID;

public interface ITransactionVelocityService {
    int getTransactionsLastHour(UUID userId);
}
