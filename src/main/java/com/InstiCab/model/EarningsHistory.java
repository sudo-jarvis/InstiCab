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
    private int earningId;
    private int cost;
    private int distanceTravelled;

}
