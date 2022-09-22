package com.InstiCab.service;

import com.InstiCab.dto.TransactionDto;
import com.InstiCab.model.Transaction;

import java.util.List;

public interface TransactionService {
    void saveTransaction(TransactionDto transactionDto);

    Transaction findByTransactionId(Long id);

    List<TransactionDto> findAllTransactions();
}