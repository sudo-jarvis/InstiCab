package com.InstiCab.service.Implementation;

import com.InstiCab.dao.RegistrationRequestDAO;
import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.models.User;
import com.InstiCab.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationRequestServiceImpl implements RegistrationRequestService {
    private final RegistrationRequestDAO registrationRequestDAO;
    @Autowired
    public RegistrationRequestServiceImpl(RegistrationRequestDAO registrationRequestDAO) {
        this.registrationRequestDAO = registrationRequestDAO;
    }

    @Override
    public void createRegistrationRequest(RegistrationRequest registrationRequest) {
        registrationRequestDAO.createRegistrationRequest(registrationRequest);
    }

    @Override
    public List<RegistrationRequest>getPendingRequest(){
        return registrationRequestDAO.getAllActiveRegistrationRequests();
    }
    @Override
    public void rejectRequest(Long driverId) {
        registrationRequestDAO.rejectRequest(driverId);
    }

    @Override
    public void acceptRequest(RegistrationRequest registrationRequest) {
        registrationRequestDAO.acceptRequest(registrationRequest);
    }

    @Override
    public RegistrationRequest getRequestByDriverId(Long driverId){
        return registrationRequestDAO.getRequestByDriverId(driverId);
    }
}
