package com.InstiCab.dao;

import com.InstiCab.models.Coupon;
import com.InstiCab.models.Transaction;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class CouponDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CouponDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
//    public void saveCoupon(Coupon coupon) {
//        final String sql = "INSERT INTO driver(coupon_id, coupon_discount, coupon_validity, max_discount, passenger_id) VALUES(?, ?, ?, ?, ?)";
//        try {
//            Random r = new Random();
//            int low = 10;
//            int high = coupon.getMaxDiscount();
//            float value = r.nextInt(high-low) + low;
//            jdbcTemplate.update(sql,coupon.getCouponId(), value, coupon.getMaxDiscount());
//        } catch (Exception e) {
//            System.out.println(e);
//            throw new DuplicateKeyException("Driver Already Registered ! !");
//        }
//    }

    public List<Coupon> getPassengerAllCoupons(Long passengerId) {
        final String sql = "SELECT * FROM coupon WHERE passenger_id=?";
        try {
            return jdbcTemplate.query(sql, RowMappers.couponRowMapper, passengerId);
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error");
        }
    }

    public void deleteCoupon(Long couponId) {
        final String sql = "DELETE FROM coupon WHERE coupon_id=?";
        try {
            jdbcTemplate.update(sql, couponId);
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error");
        }
    }
}
