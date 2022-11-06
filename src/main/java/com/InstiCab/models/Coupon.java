package com.InstiCab.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    private Long couponId;
    private int couponDiscount;
    private Date couponValidity;
    private int maxDiscount;
    private Long passengerId;

}

