package com.InstiCab.service;

import org.springframework.stereotype.Service;

@Service
public interface PassengerService {
    void savePassenger(String username);

    Long getLoggedInPassengerId();
}
