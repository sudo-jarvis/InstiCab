package com.InstiCab.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    private Long serviceId;
    private String name;
    private String type;
    private float latitudeLocation;
    private float longitudeLocation;
    private String contactNo;

}
