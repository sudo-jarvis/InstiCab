package com.InstiCab.service;

import com.InstiCab.models.Driver;

import java.util.List;

public interface DriverService {
    Driver getDriverByDriverId(Long driverId);
    Driver getDriverByUsername(String username);
    List<Driver> getPendingDrivers();
    void saveDriver(Driver driver);
}
