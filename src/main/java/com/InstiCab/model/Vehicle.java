package com.InstiCab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    @Column(nullable = false)
    private String vehicleType;
    @Column(nullable = false, unique = true)
    private String registrationNumber;
    @Column(nullable = false, unique = true)
    private String insuranceNumber;
    @Column(nullable = false)
    private String registrationState;
}
