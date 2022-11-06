package com.InstiCab.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteLocation {
    private Long locationId;
    private String label;
    private float latitudeLocation;
    private float longitudeLocation;
    private Long passengerId;

}
