package com.InstiCab.service;

import com.InstiCab.dto.TripDto;
import com.InstiCab.model.Trip;
import java.util.List;

public interface TripService {
    void saveTrip(TripDto tripDto);

    Trip findByTripId(Long id);

    List<TripDto> findAllTrips();
}