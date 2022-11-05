package com.InstiCab.service.Implementation;

import com.InstiCab.dao.ScheduledTripDAO;
import com.InstiCab.models.ScheduledTrip;
import com.InstiCab.service.ScheduledTripService;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTripServiceImpl implements ScheduledTripService {
    private final ScheduledTripDAO scheduledTripDAO;

    public ScheduledTripServiceImpl(ScheduledTripDAO scheduledTripDAO){
        this.scheduledTripDAO = scheduledTripDAO;
    }

    @Override
    public void saveScheduledTrip(ScheduledTrip scheduledTrip) throws Exception {
        scheduledTripDAO.saveScheduledTrip(scheduledTrip);
    }

    @Override
    public void delete(ScheduledTrip scheduledTrip) throws Exception {
        scheduledTripDAO.delete(scheduledTrip);
    }
}
