package com.InstiCab.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.*;

import java.sql.Time;
import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String firstName;
    private String middleName;
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    private String phoneNo;

    @NotEmpty(message = "Password should not be empty")
    private String password;
    private Boolean isDriver  = false;
    private DriverDto driver;
    private Date dateCreated;
    private Date lastLoginDate;
    private Time lastLoginTime;

}