package com.InstiCab.model;

import java.sql.Time;
import java.util.Date;

public class Transcation {
    private int transactionId;
    private Time timeTransaction;
    private Date dateTranscation;
    private int amount;
    private int status;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
