package com.InstiCab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;
    @Column(nullable = false)
    private Time timeApplied;
    @Column(nullable = false)
    private Date dateApplied;
    @Column(nullable = false)
    private int status;
    @Column(nullable = false)
    private Time timeAccepted;
    @Column(nullable = false)
    private Date dateAccepted;

}


