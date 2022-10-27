package com.InstiCab.service;

import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.models.TripRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripRequestService {
    void createTripRequest(TripRequest tripRequest);
    List<TripRequest> getPendingTripRequest();

    void rejectTripRequest(Long driverId);

    void acceptTripRequest(TripRequest tripRequest);

    TripRequest getTripRequestByTripId(Long tripId);
}
