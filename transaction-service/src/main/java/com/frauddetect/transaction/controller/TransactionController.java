package com.frauddetect.transaction.controller;

import com.frauddetect.transaction.dto.request.TransactionRequest;
import com.frauddetect.transaction.dto.request.TransactionResponse;
import com.frauddetect.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest request){
        return transactionService.createTransaction(request);
    }
}
