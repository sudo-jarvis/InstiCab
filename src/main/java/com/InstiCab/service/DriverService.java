package com.InstiCab.service;

import com.InstiCab.models.Driver;
import com.InstiCab.models.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DriverService {
    Driver getDriverByDriverId(Long driverId);
    Driver getDriverByUsername(String username);
    List<Driver> getPendingDrivers();

    Long findLoggedInDriver();
    void saveDriver(Driver driver);

}
