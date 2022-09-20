package com.InstiCab.model;

public class Service {
    private int serviceId;
    private String name;
    private String type;
    private float latitudeLocation;
    private float longitudeLocation;
    private String contactNo;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getLatitudeLocation() {
        return latitudeLocation;
    }

    public void setLatitudeLocation(float latitudeLocation) {
        this.latitudeLocation = latitudeLocation;
    }

    public float getLongitudeLocation() {
        return longitudeLocation;
    }

    public void setLongitudeLocation(float longitudeLocation) {
        this.longitudeLocation = longitudeLocation;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
