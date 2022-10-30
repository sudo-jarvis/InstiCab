package com.InstiCab.service;

import com.InstiCab.models.Transaction;
import com.InstiCab.models.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    void saveTransaction(Transaction transaction) throws Exception;

    List<Transaction> getPassengerAllTransactions(String username);

    boolean transactionPending();

    void endTransaction(String username) throws Exception;
}
