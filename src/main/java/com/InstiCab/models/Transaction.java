package com.InstiCab.models;

import java.sql.Date;
import java.sql.Time;

public class Transaction {
    private Long transactionId;
    private Time timeTransaction;
    private Date dateTranscation;
    private float amount;
    private int status;

    public Transaction(Long transactionId, Time timeTransaction, Date dateTranscation, float amount, int status) {
        this.transactionId = transactionId;
        this.timeTransaction = timeTransaction;
        this.dateTranscation = dateTranscation;
        this.amount = amount;
        this.status = status;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Time getTimeTransaction() {
        return timeTransaction;
    }

    public void setTimeTransaction(Time timeTransaction) {
        this.timeTransaction = timeTransaction;
    }

    public Date getDateTranscation() {
        return dateTranscation;
    }

    public void setDateTranscation(Date dateTranscation) {
        this.dateTranscation = dateTranscation;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
