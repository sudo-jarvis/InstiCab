package com.InstiCab.service.Implementation;

import com.InstiCab.dao.TripDAO;
import com.InstiCab.models.Trip;
import com.InstiCab.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {

    private final TripDAO tripDAO;

    @Autowired
    public TripServiceImpl(TripDAO tripDAO){
        this.tripDAO = tripDAO;
    }
    @Override
    public void saveTrip(Trip trip) throws Exception {
        tripDAO.saveTrip(trip);
    }
}
