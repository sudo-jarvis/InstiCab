package com.InstiCab.service;

import com.InstiCab.models.Transaction;
import com.InstiCab.models.Trip;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    void saveTransaction(Transaction transaction) throws Exception;
}
