package com.InstiCab.service;

import com.InstiCab.dto.DriverDto;
import com.InstiCab.model.Driver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DriverService {
    Driver saveDriver(DriverDto driverDto);

//    Driver findByDriverId(Long id);
//
//    List<DriverDto> findAllDrivers();
}