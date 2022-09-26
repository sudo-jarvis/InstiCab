package com.InstiCab.models;

public class FavouriteLocation {
    private Long locationId;
    private float latitudeLocation;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
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

    private float longitudeLocation;

}
