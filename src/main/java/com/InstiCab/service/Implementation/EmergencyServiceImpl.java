package com.InstiCab.service.Implementation;

import com.InstiCab.dao.EmergencyDAO;
import com.InstiCab.service.EmergencyService;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergencyServiceImpl implements EmergencyService {

    @Autowired
    EmergencyDAO emergencyDAO;

    @Autowired
    UserService userService;

    @Override
    public void createFireStationRequest() {
        emergencyDAO.createEmergencyRequest(0,userService.findLoggedInUsername());
    }

    @Override
    public void createPoliceRequest() {
        emergencyDAO.createEmergencyRequest(1,userService.findLoggedInUsername());
    }

    @Override
    public void createHospitalRequest() {
        emergencyDAO.createEmergencyRequest(2,userService.findLoggedInUsername());
    }
}
