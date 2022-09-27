package com.InstiCab.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EarningsHistory {
    private Long earningId;
    private float cost;
    private float distanceTravelled;

}
