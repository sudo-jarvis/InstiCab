package com.InstiCab.service.Implementation;

import com.InstiCab.dao.TransactionDAO;
import com.InstiCab.dao.TripDAO;
import com.InstiCab.models.Transaction;
import com.InstiCab.models.Trip;
import com.InstiCab.service.DriverService;
import com.InstiCab.service.PassengerService;
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
}
