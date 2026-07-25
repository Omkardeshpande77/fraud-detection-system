package com.frauddetect.transaction.service.impl;

import com.frauddetect.transaction.dto.request.TransactionRequest;
import com.frauddetect.transaction.dto.response.TransactionResponse;
import com.frauddetect.transaction.entity.Transaction;
import com.frauddetect.transaction.kafka.producer.TransactionProducer;
import com.frauddetect.transaction.mapper.TransactionMapper;
import com.frauddetect.transaction.repository.TransactionRepository;
import com.frauddetect.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final TransactionProducer producer;

    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {
        Transaction transaction = transactionMapper.toEntity(request);
        Transaction saved = transactionRepository.save(transaction);
        producer.publish(transactionMapper.toEvent(saved));
        return transactionMapper.toResponse(saved);
    }
}
