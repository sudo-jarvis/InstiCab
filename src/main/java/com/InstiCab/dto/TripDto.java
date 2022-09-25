package com.InstiCab.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {
    private Long tripId;
    @NotEmpty
    @DateTimeFormat(pattern = "dd.mm.YYYY")
    private Date startDate;
    @NotEmpty
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time startTime;
    @NotEmpty
    @DateTimeFormat(pattern = "dd.mm.YYYY")
    private Date endDate;
    @NotEmpty
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time endTime;
    @NotEmpty
    private int status;
    @NotEmpty
    private float startLatitude;
    @NotEmpty
    private float startLongitude;
    @NotEmpty
    private float endLatitude;
    @NotEmpty
    private float endLongitude;
}