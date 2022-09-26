package com.InstiCab.models;

public class TripRequest {
    private int tripRequestId;
    private User user;
    private Driver driver;
    private Trip trip;

    public TripRequest(int tripRequestId, User user, Driver driver, Trip trip) {
        this.tripRequestId = tripRequestId;
        this.user = user;
        this.driver = driver;
        this.trip = trip;
    }

    public int getTripRequestId() {
        return tripRequestId;
    }

    public void setTripRequestId(int tripRequestId) {
        this.tripRequestId = tripRequestId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
