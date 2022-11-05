package com.InstiCab.service;

import com.InstiCab.models.ScheduledTrip;
import org.springframework.stereotype.Service;

@Service
public interface ScheduledTripService {
    void saveScheduledTrip(ScheduledTrip scheduledTrip) throws Exception;

    void delete(ScheduledTrip scheduledTrip) throws Exception;
}
