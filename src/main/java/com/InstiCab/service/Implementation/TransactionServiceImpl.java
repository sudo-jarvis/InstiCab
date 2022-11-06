package com.InstiCab.service.Implementation;

import com.InstiCab.dao.TransactionDAO;
import com.InstiCab.models.Transaction;
import com.InstiCab.service.TransactionService;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDAO transactionDAO;
    private final UserService userService;
    @Autowired
    public TransactionServiceImpl(TransactionDAO transactionDAO, UserService userService){
        this.transactionDAO = transactionDAO;
        this.userService = userService;
    }
    @Override
    public void saveTransaction(Transaction transaction) throws Exception {
        transactionDAO.saveTransaction(transaction);
    }

    @Override
    public List<Transaction> getPassengerAllTransactions(String username) {
        return transactionDAO.getPassengerAllTransactions(username);
    }

    @Override
    public boolean transactionPending() {
        String username = userService.findLoggedInUsername();
        return transactionDAO.transactionPending(username);
    }

    @Override
    public void endTransaction(String username) throws Exception {
        transactionDAO.endTransaction(username);
    }
    @Override
    public void changeTransactionStatus(Long transactionId, Integer status)throws Exception{
        transactionDAO.changeTransactionStatus(transactionId,status);
    }

    @Override
    public Transaction getTransaction(Long transactionId) throws Exception {
        return transactionDAO.getTransaction(transactionId);
    }


}
