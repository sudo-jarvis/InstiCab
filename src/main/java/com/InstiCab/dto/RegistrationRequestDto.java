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
public class RegistrationRequestDto {
    private Long requestId;
    @NotEmpty
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time timeApplied;
    @NotEmpty
    @DateTimeFormat(pattern = "dd.mm.YYYY")
    private Date dateApplied;
    @NotEmpty
    private int status;
    @NotEmpty
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time timeAccepted;
    @NotEmpty
    @DateTimeFormat(pattern = "dd.mm.YYYY")
    private Date dateAccepted;
}