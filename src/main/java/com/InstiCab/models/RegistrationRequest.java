package com.InstiCab.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private Long requestId;
    private Time timeApplied;
    private Date dateApplied;
    private int status;
    private Time timeAccepted;
    private Date dateAccepted;

}
