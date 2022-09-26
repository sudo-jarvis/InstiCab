package com.InstiCab.models;

import java.sql.Date;
import java.sql.Time;

public class Trip {
    private Long tripId;
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;
    private int status;
    private float startLatitude;
    private float startLongitude;
    private float endLatitude;
    private float endLongitude;

    public Trip(Long tripId, Date startDate, Time startTime, Date endDate, Time endTime, int status, float startLatitude, float startLongitude, float endLatitude, float endLongitude) {
        this.tripId = tripId;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.status = status;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(float startLatitude) {
        this.startLatitude = startLatitude;
    }

    public float getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(float startLongitude) {
        this.startLongitude = startLongitude;
    }

    public float getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(float endLatitude) {
        this.endLatitude = endLatitude;
    }

    public float getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(float endLongitude) {
        this.endLongitude = endLongitude;
    }
}
