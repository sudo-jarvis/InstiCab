package com.InstiCab.model;

import java.util.Date;

public class Coupon {
    private int couponId;
    private float couponDiscount;
    private Date couponValidity;
    private int maxDiscount;

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public float getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(float couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public Date getCouponValidity() {
        return couponValidity;
    }

    public void setCouponValidity(Date couponValidity) {
        this.couponValidity = couponValidity;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }
}
