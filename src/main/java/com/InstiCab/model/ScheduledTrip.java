package com.InstiCab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ScheduledTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduledTripId;
    private Time tripTime;
    private float startLatitude;
    private float startLongitude;
    private float endLatitude;
    private float endLongitude;

}
