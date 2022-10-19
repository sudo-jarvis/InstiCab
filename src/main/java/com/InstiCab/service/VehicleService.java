package com.InstiCab.service;

import com.InstiCab.models.Vehicle;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {
    void saveVehicle(Vehicle vehicle);
}
