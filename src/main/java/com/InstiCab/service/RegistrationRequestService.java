package com.InstiCab.service;

import com.InstiCab.models.RegistrationRequest;

import java.util.List;

public interface RegistrationRequestService {
    List<RegistrationRequest> getPendingRequest();
}
