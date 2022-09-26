package com.InstiCab.models;


import java.util.Date;

public class Coupon {
    private Long couponId;
    private float couponDiscount;
    private Date couponValidity;
    private int maxDiscount;

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public void setCouponDiscount(float couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public void setCouponValidity(Date couponValidity) {
        this.couponValidity = couponValidity;
    }

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public Long getCouponId() {
        return couponId;
    }

    public float getCouponDiscount() {
        return couponDiscount;
    }

    public Date getCouponValidity() {
        return couponValidity;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }
}

