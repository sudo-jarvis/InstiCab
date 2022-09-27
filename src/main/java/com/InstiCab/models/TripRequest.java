package com.InstiCab.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripRequest {
    private int tripRequestId;
    private User user;
    private Driver driver;
    private int tripId;

}
