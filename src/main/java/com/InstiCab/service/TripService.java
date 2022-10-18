package com.InstiCab.service;

import com.InstiCab.models.Driver;
import com.InstiCab.models.Trip;
import org.springframework.stereotype.Service;

@Service
public interface TripService {
    void saveTrip(Trip trip) throws Exception;

    boolean tripAlreadyExists();
    Trip getTripByTripId(Long tripId);
}
