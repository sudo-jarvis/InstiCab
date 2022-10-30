package com.InstiCab.controllers;

import com.InstiCab.models.ChargeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(@RequestParam(name = "amountToPay") String amountToPay, Model model) {
        model.addAttribute("amount", Integer.parseInt(amountToPay) * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.INR);
        return "checkout";
    }
}
