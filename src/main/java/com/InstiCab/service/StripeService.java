package com.InstiCab.service;

import com.InstiCab.models.ChargeRequest;
import com.stripe.exception.*;

import com.stripe.model.PaymentIntent;
import org.springframework.stereotype.Service;

@Service
public interface StripeService  {
    PaymentIntent charge(ChargeRequest chargeRequest) throws StripeException;
}
