package com.InstiCab.service;

import com.InstiCab.dto.DriverDto;
import com.InstiCab.model.Driver;
import java.util.List;

public interface DriverService {
    void saveDriver(DriverDto driverDto);

    Driver findByDriverId(Long id);

    List<DriverDto> findAllDrivers();
}