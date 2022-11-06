package com.InstiCab.service.Implementation;

import com.InstiCab.dao.TransactionDisputeDAO;
import com.InstiCab.models.TransactionDispute;
import com.InstiCab.service.TransactionDisputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionDisputeServiceImpl implements TransactionDisputeService {

    @Autowired
    private TransactionDisputeDAO transactionDisputeDAO;

    public TransactionDisputeServiceImpl(TransactionDisputeDAO transactionDisputeDAO){
        this.transactionDisputeDAO = transactionDisputeDAO;
    }
    @Override
    public void saveDispute(TransactionDispute transactionDispute) throws Exception {
        transactionDisputeDAO.saveDispute(transactionDispute);
    }

    @Override
    public List<TransactionDispute> getDisputes() {
        return transactionDisputeDAO.getAllDisputes();
    }

    @Override
    public void changeDisputeStatus(Long disputeId, Integer status) {
        transactionDisputeDAO.changeDisputeStatus(disputeId,status);
    }
}
