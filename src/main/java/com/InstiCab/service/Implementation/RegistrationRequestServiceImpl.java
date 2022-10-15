package com.InstiCab.service.Implementation;

import com.InstiCab.dao.RegistrationRequestDAO;
import com.InstiCab.models.RegistrationRequest;
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
    public List<RegistrationRequest>getPendingRequest(){
        return registrationRequestDAO.getAllActiveRegistrationRequests();
    }
}
