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
public class EarningsHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long earningId;
    private float cost;
    private float distanceTravelled;

}
