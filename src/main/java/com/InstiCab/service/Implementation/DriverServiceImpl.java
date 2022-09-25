package com.InstiCab.service.Implementation;

import com.InstiCab.dto.DriverDto;
import com.InstiCab.model.Driver;
import com.InstiCab.repository.DriverRepository;
import com.InstiCab.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DriverServiceImpl implements DriverService {
    @Autowired

    private DriverRepository driverRepository;

    @Override
    public Driver saveDriver(DriverDto driverDto) {
        Driver driver = new Driver();
        driver.setAadharNumber(driverDto.getAadharNumber());
        driver.setAccountName(driverDto.getAccountName());
        driver.setAccountNo(driverDto.getAccountNo());
        driver.setLicenseNumber(driverDto.getLicenseNumber());
        driver.setBankName(driverDto.getBankName());
        driver.setIfscCode(driverDto.getIfscCode());
        return driver;
    }

}
