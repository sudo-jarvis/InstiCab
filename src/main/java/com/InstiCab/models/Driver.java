package com.InstiCab.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    private Long driverId;
    private String licenseNumber;
    private String aadharNumber;
    private String accountNo;
    private String accountName;
    private String ifscCode;
    private String bankName;
    private String username;

}
