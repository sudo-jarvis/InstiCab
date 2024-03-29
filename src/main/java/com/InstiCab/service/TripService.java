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

    void endTrip(Trip trip);

    Trip getScheduledTrip(Long passengerId);

    void changeTripStatus(Long tripId, int status);

    void cancelTrip(Long tripId);
    boolean checkValidTrip(Trip trip);

    void saveFeedback(String feedback, Long tripId);

    List<Trip> getDriverPreviousTrips(Long driverId) throws Exception;

    void updateTrip(Trip trip);

//    List<Trip> getDriverCurrentTrips(Long driverId) throws Exception;
}
