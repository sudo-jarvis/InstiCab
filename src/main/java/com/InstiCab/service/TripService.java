package com.InstiCab.service;

import com.InstiCab.models.Driver;
import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.models.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripService {
    void saveTrip(Trip trip) throws Exception;

    boolean tripAlreadyExists();

    boolean tripAlreadyRunning() throws Exception;

    List<Trip> getTripReqList() throws Exception;
    Trip getTripByTripId(Long tripId);
    List<Trip> getPassengerAllTrips(Long passengerId);
    void rejectTripRequest(Long tripId);
    void acceptTripRequest(Trip trip);
    List<Trip> getTripList() throws Exception;
}
