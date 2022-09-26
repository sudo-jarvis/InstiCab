package com.InstiCab.models;

public class Vehicle {
    private Long vehicleId;
    private String vehicleType;
    private String registrationNumber;
    private String insuranceNumber;
    private String registrationState;

    public Vehicle(Long vehicleId, String vehicleType, String registrationNumber, String insuranceNumber, String registrationState) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.registrationNumber = registrationNumber;
        this.insuranceNumber = insuranceNumber;
        this.registrationState = registrationState;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getRegistrationState() {
        return registrationState;
    }

    public void setRegistrationState(String registrationState) {
        this.registrationState = registrationState;
    }
}
