package com.InstiCab.models;

import java.sql.Time;
import java.util.Date;

public class RegistrationRequest {
    private Long requestId;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Time getTimeApplied() {
        return timeApplied;
    }

    public void setTimeApplied(Time timeApplied) {
        this.timeApplied = timeApplied;
    }

    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Time getTimeAccepted() {
        return timeAccepted;
    }

    public void setTimeAccepted(Time timeAccepted) {
        this.timeAccepted = timeAccepted;
    }

    public Date getDateAccepted() {
        return dateAccepted;
    }

    public void setDateAccepted(Date dateAccepted) {
        this.dateAccepted = dateAccepted;
    }

    private Time timeApplied;
    private Date dateApplied;
    private int status;
    private Time timeAccepted;
    private Date dateAccepted;
}
