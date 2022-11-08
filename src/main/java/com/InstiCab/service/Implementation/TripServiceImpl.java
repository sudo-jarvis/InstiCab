package com.InstiCab.service.Implementation;

import com.InstiCab.dao.TripDAO;
import com.InstiCab.models.Trip;
import com.InstiCab.service.DriverService;
import com.InstiCab.service.PassengerService;
import com.InstiCab.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {
    private final TripDAO tripDAO;
    private final PassengerService passengerService;
    private final DriverService driverService;
    
    @Autowired
    public TripServiceImpl(TripDAO tripDAO, PassengerService passengerService, DriverService driverService){
        this.tripDAO = tripDAO;
        this.passengerService = passengerService;
        this.driverService = driverService;
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
    public boolean tripAlreadyRunning() throws Exception {
        Long driverId = driverService.findLoggedInDriver();
        return tripDAO.tripAlreadyRunning(driverId);
    }
    @Override
    public List<Trip> getTripReqList() throws Exception {
        return tripDAO.getTripReqList();
    }

    @Override
    public Trip getTripByTripId(Long tripId) {
        return tripDAO.getTripByTripId(tripId);
    }

    @Override
    public List<Trip> getPassengerAllTrips(Long passengerId) {
        return tripDAO.getPassengerAllTrips(passengerId);
    }

    @Override
    public void rejectTripRequest(Long tripId) {
        tripDAO.rejectTripRequest(tripId);
    }

    @Override
    public void acceptTripRequest(Trip trip) {
        tripDAO.acceptTripRequest(trip);
    }

    @Override
    public List<Trip> getTripList() throws Exception {
        return tripDAO.getTripList();
    }

    @Override
    public void endTrip(Trip trip) {
        tripDAO.endTrip(trip);
    }

    @Override
    public Trip getScheduledTrip(Long passengerId) {
        return tripDAO.getScheduledTrip(passengerId);
    }

    @Override
    public void changeTripStatus(Long tripId, int status) {
        tripDAO.changeTripStatus(tripId,status);
    }

    @Override
    public void cancelTrip(Long tripId) {
        tripDAO.cancelTrip(tripId);
    }

    @Override
    public boolean checkValidTrip(Trip trip) {
        if(trip.getEndLongitude()== trip.getStartLongitude() && trip.getStartLatitude() == trip.getEndLatitude())
            return false;
        if(trip.getEndLatitude() == 0.0 || trip.getEndLongitude() == 0.0 || trip.getStartLatitude() == 0.0 || trip.getStartLongitude() == 0.0)
            return false;
        return true;
    }

    @Override
    public void saveFeedback(String feedback, Long tripId) {
        tripDAO.saveFeedback(feedback, tripId);
    }

    @Override
    public List<Trip> getDriverPreviousTrips(Long driverId) throws Exception {
        return tripDAO.getDriverPreviousTrips(driverId);
    }

    @Override
    public void updateTrip(Trip trip) {
        tripDAO.updateTrip(trip);
    }

//    @Override
//    public List<Trip> getDriverCurrentTrips(Long driverId) throws Exception {
//        return tripDAO.getDriverCurrentTrips(driverId);
//    }
}
