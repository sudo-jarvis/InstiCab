package com.InstiCab.service;

import com.InstiCab.dto.ScheduledTripDto;
import com.InstiCab.model.ScheduledTrip;
import java.util.List;

public interface ScheduledTripService {
    void saveTrip(ScheduledTripDto scheduledTripDto);

    ScheduledTrip findByscheduledTripId(Long id);

    List<ScheduledTripDto> findAllTrips();
}