package com.InstiCab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Time startTime;
    @Column(nullable = false)
    private Date endDate;
    @Column(nullable = false)
    private Time endTime;
    @Column(nullable = false)
    private int status;
    @Column(nullable = false)
    private float startLatitude;
    @Column(nullable = false)
    private float startLongitude;
    @Column(nullable = false)
    private float endLatitude;
    @Column(nullable = false)
    private float endLongitude;
}
