package com.InstiCab.service;

import com.InstiCab.models.RegistrationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegistrationRequestService {
    void createRegistrationRequest(RegistrationRequest registrationRequest);
    List<RegistrationRequest> getPendingRequest();

    void rejectRequest(Long driverId);

    void acceptRequest(RegistrationRequest registrationRequest);

    RegistrationRequest getRequestByDriverId(Long driverId);
}
