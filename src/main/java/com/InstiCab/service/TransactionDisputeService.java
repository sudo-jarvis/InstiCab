package com.InstiCab.service;

import com.InstiCab.models.TransactionDispute;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionDisputeService {

    void saveDispute(TransactionDispute transactionDispute) throws Exception;

    List<TransactionDispute> getDisputes();

    void changeDisputeStatus(Long disputeId,Integer status);
}
