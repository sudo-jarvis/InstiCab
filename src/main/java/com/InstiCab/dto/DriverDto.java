package com.InstiCab.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {
    private Long driverId;
    @NotEmpty(message = "License number should not be empty")
    private String licenseNumber;
    @NotEmpty(message = "Aadhar number should not be empty")
    private String aadharNumber;
    @NotEmpty
    private String accountNo;
    @NotEmpty
    private String accountName;
    @NotEmpty
    private String ifscCode;
    @NotEmpty
    private String bankName;
}