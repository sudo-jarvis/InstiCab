package com.InstiCab.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    private Long tripId;
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;
    private int status;
    private float startLatitude;
    private float startLongitude;
    private float endLatitude;
    private float endLongitude;
    private int driverId;

}
