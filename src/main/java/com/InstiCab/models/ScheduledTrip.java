package com.InstiCab.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledTrip {
    private Long scheduledTripId;
    private Time tripTime;
    private float startLatitude;
    private float startLongitude;
    private float endLatitude;
    private float endLongitude;

}
