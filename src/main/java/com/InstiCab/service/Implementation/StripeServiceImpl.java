package com.InstiCab.service.Implementation;

import com.InstiCab.models.ChargeRequest;
import com.InstiCab.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("${STRIPE_SECRET_KEY}")
    String secretKey;
    @PostConstruct
    public void init() {
        Stripe.apiKey= secretKey;
    }
    public PaymentIntent charge(ChargeRequest chargeRequest)
            throws StripeException {
        List<Object> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("payment_method_types", paymentMethodTypes);
        return PaymentIntent.create(chargeParams);
    }
}
