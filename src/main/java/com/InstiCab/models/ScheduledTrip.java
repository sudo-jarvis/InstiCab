package com.InstiCab.models;

import java.sql.Time;

public class ScheduledTrip {
    private Long scheduledTripId;
    private Time tripTime;
    private float startLatitude;
    private float startLongitude;
    private float endLatitude;
    private float endLongitude;

    public ScheduledTrip(Long scheduledTripId, Time tripTime, float startLatitude, float startLongitude, float endLatitude, float endLongitude) {
        this.scheduledTripId = scheduledTripId;
        this.tripTime = tripTime;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
    }

    public Long getScheduledTripId() {
        return scheduledTripId;
    }

    public void setScheduledTripId(Long scheduledTripId) {
        this.scheduledTripId = scheduledTripId;
    }

    public Time getTripTime() {
        return tripTime;
    }

    public void setTripTime(Time tripTime) {
        this.tripTime = tripTime;
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
