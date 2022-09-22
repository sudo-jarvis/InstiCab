package com.InstiCab.service;

import com.InstiCab.dto.PassengerDto;
import com.InstiCab.model.Passenger;
import java.util.List;

public interface PassengerService {
    void savePassenger(PassengerDto passengerDto);

    Passenger findByPassengerId(Long id);

    List<PassengerDto> findAllPassengers();
}