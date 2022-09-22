package com.InstiCab.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledTripDto {
    private Long scheduledTripId;
    @NotEmpty
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time timeApplied;
    @NotEmpty
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time tripTime;
    @NotEmpty
    private float startLatitude;
    @NotEmpty
    private float startLongitude;
    @NotEmpty
    private float endLatitude;
    @NotEmpty
    private float endLongitude;

}