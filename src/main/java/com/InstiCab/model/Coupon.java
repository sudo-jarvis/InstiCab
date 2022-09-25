package com.InstiCab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;
    @Column(nullable = false)
    private float couponDiscount;
    @Column(nullable = false)
    private Date couponValidity;
    @Column(nullable = false)
    private int maxDiscount;
}
