package com.InstiCab.service.Implementation;

import com.InstiCab.dao.CouponDAO;
import com.InstiCab.models.Coupon;
import com.InstiCab.service.CouponService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    private final CouponDAO couponDAO;

    public CouponServiceImpl(CouponDAO couponDAO){
        this.couponDAO = couponDAO;
    }
    @Override
    public void saveCoupon(Coupon coupon) {
        couponDAO.saveCoupon(coupon);
    }

    @Override
    public List<Coupon> getPassengerAllCoupons(Long passengerId) {
        return couponDAO.getPassengerAllCoupons(passengerId);
    }

    @Override
    public void deleteCoupon(Long couponId) {
        couponDAO.deleteCoupon(couponId);
    }

//    @Override
//    public List<CouponBeneficiary> getCouponBeneficiaries(Date sinceDate, Integer numCoupons) {
//        return couponDAO.getCouponBeneficiaries(sinceDate, numCoupons);
//    }
}
