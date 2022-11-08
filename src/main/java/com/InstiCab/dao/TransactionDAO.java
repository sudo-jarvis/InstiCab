package com.InstiCab.dao;

import com.InstiCab.models.Transaction;
import com.InstiCab.models.Trip;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class TransactionDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveTransaction(Transaction transaction) throws Exception {
        final String sql = "INSERT INTO transaction(trip_id,status,amount,username,date_transaction,time_transaction)" +
                " VALUES (?,?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,transaction.getTripId(),transaction.getStatus(),transaction.getAmount(),
                    transaction.getUsername(),
                    transaction.getDateTransaction(), transaction.getTimeTransaction());
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("error in inserting into transcation");
        }
    }

    public List<Transaction> getPassengerAllTransactions(String username) {
        final String sql = "SELECT * FROM " +
                "transaction as t WHERE" +
                " t.username = ? order by t.transaction_id";
        try {
            return jdbcTemplate.query(sql, RowMappers.transactionRowMapper, username);
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error in getting passenger transcation");
        }
    }

    public boolean transactionPending(String username) {
        final String sql = "SELECT * FROM transaction WHERE status=0 AND username=?";
        try {
            return !jdbcTemplate.query(sql, RowMappers.transactionRowMapper, username).isEmpty();
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error in getting pending transcation");
        }
    }

    public void endTransaction(String username) throws Exception {
        final String sql = "UPDATE transaction SET status = 1, time_transaction = ?, date_transaction = ? WHERE " +
                "username = ?";
        try {
            jdbcTemplate.update(sql, Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()), username);
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("error in ending transcation");
        }
    }


    public void changeTransactionStatus(Long transactionId, Integer status) throws Exception {
        final String sql = "UPDATE transaction SET status = ? WHERE " +
                "transaction_id = ?";
        try {
            jdbcTemplate.update(sql, status,transactionId);
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("error in changing transaction status");
        }
    }

    public Transaction getTransaction(Long transactionId) throws Exception{
        final String sql = "SELECT * FROM transaction WHERE transaction_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, RowMappers.transactionRowMapper,transactionId);
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("error in getting transaction");
        }
    }

    public void updateTransactionDateTime(Transaction transaction) throws Exception {
        final String sql = "UPDATE transaction SET date_transaction=?, time_transaction=?";
        try {
            jdbcTemplate.update(sql,transaction.getDateTransaction(), transaction.getTimeTransaction());
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("error in updating transaction date");
        }
    }
}
