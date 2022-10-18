package com.InstiCab.service.Implementation;

import com.InstiCab.dao.TripDAO;
import com.InstiCab.models.Driver;
import com.InstiCab.models.Trip;
import com.InstiCab.service.PassengerService;
import com.InstiCab.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {
    private final TripDAO tripDAO;
    private final PassengerService passengerService;

    @Autowired
    public TripServiceImpl(TripDAO tripDAO, PassengerService passengerService){
        this.tripDAO = tripDAO;
        this.passengerService = passengerService;
    }
    @Override
    public void saveTrip(Trip trip) throws Exception {
        tripDAO.saveTrip(trip);
    }

    @Override
    public boolean tripAlreadyExists() {
        Long passengerId = passengerService.getLoggedInPassengerId();
        return tripDAO.tripAlreadyExists(passengerId);
    }

    @Override
    public Trip getTripByTripId(Long tripId) {
        return tripDAO.getTripByTripId(tripId);
    }

    @Override
    public List<Trip> getPassengerAllTrips(Long passengerId) {
        return tripDAO.getPassengerAllTrips(passengerId);
    }
}
