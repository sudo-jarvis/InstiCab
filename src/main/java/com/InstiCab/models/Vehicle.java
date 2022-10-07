package com.InstiCab.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    private Long vehicleId;
    private String vehicleType;
    private String registrationNumber;
    private String insuranceNumber;
    private String registrationState;
    private Long driverId;

}
