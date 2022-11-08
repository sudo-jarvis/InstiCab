package com.InstiCab.service;

import org.springframework.stereotype.Service;

@Service
public interface EmergencyService {
    public void createFireStationRequest();

    public void createPoliceRequest();

    public void createHospitalRequest();
}
