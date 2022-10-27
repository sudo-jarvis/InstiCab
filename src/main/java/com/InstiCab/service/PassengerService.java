package com.InstiCab.service;

import com.InstiCab.models.Driver;
import com.InstiCab.models.Passenger;
import org.springframework.stereotype.Service;

@Service
public interface PassengerService {
    void savePassenger(String username);

    Long getLoggedInPassengerId();
    Passenger getPassengerByPassengerId(Long passengerId);
}
