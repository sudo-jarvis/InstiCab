package com.InstiCab.service;

import com.InstiCab.models.Driver;

public interface DriverService {
    Driver getDriverByDriverId(Long driverId);
    Driver getDriverByUsername(String username);

    void saveDriver(Driver driver);
}
