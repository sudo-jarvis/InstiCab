package com.InstiCab.service;

import com.InstiCab.dto.VehicleDto;
import com.InstiCab.model.Vehicle;
import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDto vehicleDto);

    Vehicle findByVehicleId(Long id);

    List<VehicleDto> findAllVehicles();
}