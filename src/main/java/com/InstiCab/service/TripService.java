package com.InstiCab.service;

import com.InstiCab.models.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripService {
    void saveTrip(Trip trip) throws Exception;

    boolean tripAlreadyExists();

    List<Trip> getTripList() throws Exception;
}
