package com.InstiCab.models;

public class FavouriteLocation {
    private Long locationId;
    private float latitudeLocation;
    private float longitudeLocation;

    public FavouriteLocation(Long locationId, float latitudeLocation, float longitudeLocation) {
        this.locationId = locationId;
        this.latitudeLocation = latitudeLocation;
        this.longitudeLocation = longitudeLocation;
    }

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
}
