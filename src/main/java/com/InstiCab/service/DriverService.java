package com.InstiCab.service;

import com.InstiCab.models.Driver;

public interface DriverService {
    Driver getDriverByDriverId(Integer driverId);
    void saveDriver(Driver driver);

}
