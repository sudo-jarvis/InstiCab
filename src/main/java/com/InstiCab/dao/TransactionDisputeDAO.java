package com.InstiCab.dao;

import com.InstiCab.models.TransactionDispute;
import com.InstiCab.service.TransactionDisputeService;
import com.InstiCab.utils.RowMappers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class TransactionDisputeDAO {
    private JdbcTemplate jdbcTemplate;

    public TransactionDisputeDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveDispute(TransactionDispute transactionDispute) throws Exception {
        final String sql = "INSERT INTO transaction_dispute(status,transaction_id ,description) VALUES (0,?,?)";
        try {
            jdbcTemplate.update(sql, transactionDispute.getTransactionId(),transactionDispute.getDescription());
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception(e);
        }
    }

    public List<TransactionDispute> getAllDisputes(){
        final String sql = "SELECT * FROM transaction_dispute";
        try {
            return jdbcTemplate.query(sql, RowMappers.transactionDisputeRowMapper);
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error");
        }

    }

    public void changeDisputeStatus(Long transactionId, Integer status) {
        final String sql = "UPDATE transaction_dispute SET status = ? WHERE transaction_id = ?";
        try {
            jdbcTemplate.update(sql,status,transactionId);
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error");
        }
    }
}
