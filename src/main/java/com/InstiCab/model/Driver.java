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
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;
    @Column(nullable = false, unique = true)
    private String licenseNumber;
    @Column(nullable = false, unique = true)
    private String aadharNumber;
    @Column(nullable = false, unique = true)
    private String accountNo;
    @Column(nullable = false)
    private String accountName;
    @Column(nullable = false)
    private String ifscCode;
    @Column(nullable = false)
    private String bankName;

    @OneToOne(mappedBy = "driver")
    private User user;

}
