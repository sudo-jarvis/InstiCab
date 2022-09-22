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
public class VehicleDto {
    private Long vehicleId;
    @NotEmpty
    private String vehicleType;
    @NotEmpty(message = "Registration number should not be empty")
    private String registrationNumber;
    @NotEmpty(message = "Insurance number should not be empty")
    private String insuranceNumber;
    @NotEmpty
    private String registrationState;
}