package com.InstiCab.service.Implementation;

import com.InstiCab.dao.DriverDAO;
import com.InstiCab.models.Driver;
import com.InstiCab.service.DriverService;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverDAO driverDAO;
    private final UserService userService;

    @Autowired
    public DriverServiceImpl(DriverDAO driverDAO,UserService userService){
        this.driverDAO = driverDAO;
        this.userService = userService;
    }

    @Override
    public Driver getDriverByDriverId(Long driverId) {
        return driverDAO.getDriverDataBydriverId(driverId);
    }

    @Override
    public Driver getDriverByUsername(String username) {
        return driverDAO.getDriverDataByUsername(username);
    }

    @Override
    public void saveDriver(Driver driver) {
        driverDAO.createDriver(driver);
    }

    @Override
    public List<Driver> getPendingDrivers() {
        return driverDAO.getAllPendingDrivers();
    }

    @Override
    public Long findLoggedInDriver() {
        String username = userService.findLoggedInUsername();
        Driver driver = driverDAO.getDriverDataByUsername(username);
        return driver.getDriverId();
    }
}
