package com.InstiCab.controllers;

import com.InstiCab.models.ChargeRequest;
import com.InstiCab.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {
    private CouponService couponService;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @Autowired
    public CheckoutController(CouponService couponService){
        this.couponService = couponService;
    }

    @RequestMapping("/checkout")
    public String checkout(@RequestParam(name = "amountToPay") String amountToPay, @RequestParam(name = "couponId") Long couponId, Model model) {
        model.addAttribute("amount", Integer.parseInt(amountToPay) * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.INR);
        couponService.deleteCoupon(couponId);
        return "checkout";
    }
}
