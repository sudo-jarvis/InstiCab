package com.InstiCab.service;

import com.InstiCab.dto.CouponDto;
import com.InstiCab.model.Coupon;
import java.util.List;

public interface CouponService {
    void saveCoupon(CouponDto couponDto);

    Coupon findByCouponId(Long id);

    List<CouponDto> findAllCoupons();
}