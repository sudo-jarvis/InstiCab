package com.InstiCab.service;

import com.InstiCab.models.Emergency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmergencyService {
    public void createFireStationRequest();

    public void createPoliceRequest();

    public void createHospitalRequest();

    List<Emergency> getEmergencyRequests();

    void deleteRequest();
}
