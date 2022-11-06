package com.InstiCab.service;

import com.InstiCab.models.Coupon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CouponService {
//    void saveCoupon(Coupon coupon);

    List<Coupon> getPassengerAllCoupons(Long passengerId);

    void deleteCoupon(Long couponId);
}
