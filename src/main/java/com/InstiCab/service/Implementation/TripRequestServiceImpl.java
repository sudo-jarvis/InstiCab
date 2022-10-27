package com.InstiCab.service.Implementation;

import com.InstiCab.dao.RegistrationRequestDAO;
import com.InstiCab.dao.TripRequestDAO;
import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.models.TripRequest;
import com.InstiCab.service.RegistrationRequestService;
import com.InstiCab.service.TripRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripRequestServiceImpl implements TripRequestService {
    private final TripRequestDAO tripRequestDAO;
    @Autowired
    public TripRequestServiceImpl(TripRequestDAO tripRequestDAO) {
        this.tripRequestDAO = tripRequestDAO;
    }

    @Override
    public void createTripRequest(TripRequest tripRequest) {
        tripRequestDAO.createTripRequest(tripRequest);
    }

    @Override
    public List<TripRequest>getPendingTripRequest(){
        return tripRequestDAO.getAllActiveTripRequests();
    }

    @Override
    public void rejectTripRequest(Long driverId) {
        tripRequestDAO.rejectTripRequest(driverId);
    }

    @Override
    public void acceptTripRequest(TripRequest tripRequest) {
        tripRequestDAO.acceptTripRequest(tripRequest);
    }

    @Override
    public TripRequest getTripRequestByTripId(Long tripId){
        return tripRequestDAO.getTripRequestByTripId(tripId);
    }
}
