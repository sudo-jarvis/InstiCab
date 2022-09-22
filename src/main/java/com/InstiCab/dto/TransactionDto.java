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
public class TransactionDto {
    private Long transactionId;
    @NotEmpty
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time timeTransaction;
    @NotEmpty
    @DateTimeFormat(pattern = "dd.mm.YYYY")
    private Date dateTranscation;
    @NotEmpty
    private float amount;
    @NotEmpty
    private int status;
}