package com.frauddetect.transaction.mapper;

import com.frauddetect.enums.TransactionStatus;
import com.frauddetect.events.TransactionCreatedEvent;
import com.frauddetect.transaction.dto.request.TransactionRequest;
import com.frauddetect.transaction.dto.response.TransactionResponse;
import com.frauddetect.transaction.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionMapper {

    private TransactionMapper(){}

    public  Transaction toEntity(TransactionRequest transactionRequest){
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setUserId(transactionRequest.userId());
        transaction.setMerchantId(transactionRequest.merchantId());
        transaction.setAmount(transactionRequest.amount());
        transaction.setCurrency(transactionRequest.currency());
        transaction.setMerchantName(transactionRequest.merchantName());
        transaction.setMerchantCategory(transactionRequest.merchantCategory());
        transaction.setCountry(transactionRequest.country());
        transaction.setPaymentMethod(transactionRequest.paymentMethod());
        transaction.setStatus(TransactionStatus.PENDING);
        return transaction;
    }

    public  TransactionResponse toResponse(Transaction transaction){
        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getUserId(),
                transaction.getMerchantId(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getMerchantName(),
                transaction.getMerchantCategory(),
                transaction.getCountry(),
                transaction.getPaymentMethod(),
                transaction.getStatus(),
                transaction.getCreatedAt()
        );
    }

    public TransactionCreatedEvent toEvent(Transaction transaction) {

        return new TransactionCreatedEvent(
                transaction.getTransactionId(),
                transaction.getUserId(),
                transaction.getMerchantId(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getMerchantName(),
                transaction.getMerchantCategory(),
                transaction.getCountry(),
                transaction.getPaymentMethod(),
                transaction.getStatus(),
                transaction.getFeatures(),
                transaction.getCreatedAt()
        );
    }
}
