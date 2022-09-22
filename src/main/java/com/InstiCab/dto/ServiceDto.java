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
public class ServiceDto {
    private Long serviceId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String type;
    @NotEmpty
    private float latitudeLocation;
    @NotEmpty
    private float longitudeLocation;
    @NotEmpty(message = "Contact no. should not be empty")
    private String contactNo;
}