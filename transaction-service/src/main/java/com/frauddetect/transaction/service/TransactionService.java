package com.frauddetect.transaction.service;

import com.frauddetect.transaction.dto.request.TransactionRequest;
import com.frauddetect.transaction.dto.request.TransactionResponse;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest request);
}
