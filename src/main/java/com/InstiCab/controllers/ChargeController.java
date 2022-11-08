package com.InstiCab.controllers;

import com.InstiCab.models.*;
import com.InstiCab.service.EarningsHistoryService;
import com.InstiCab.service.StripeService;
import com.InstiCab.service.TransactionService;
import com.InstiCab.service.TripService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Log
@Controller
public class ChargeController {

    @Autowired
    StripeService paymentsService;

    @Autowired
    TripService tripService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    EarningsHistoryService earningsHistoryService;

    public double distance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;
            return (dist);
        }
    }

    @GetMapping("/charge")
    public String invalid() {
        return "redirect:/";
    }

    @PostMapping("/charge")
    public String charge(@RequestParam(name = "transactionId") String transactionId, @RequestParam(name = "tripId") String tripId, ChargeRequest chargeRequest,
                         Model model) throws Exception {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.INR);
        PaymentIntent charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("transactionId", Long.valueOf(transactionId));
        model.addAttribute("tripId", Long.valueOf(tripId));
        System.out.println(transactionId);
        Transaction transaction = transactionService.getTransaction(Long.valueOf(transactionId));
        transaction.setDateTransaction(Date.valueOf(LocalDate.now()));
        transaction.setTimeTransaction(Time.valueOf(LocalTime.now()));
        transactionService.updateTransactionDateTime(transaction);
        Trip trip = tripService.getTripByTripId(transaction.getTripId());
        tripService.changeTripStatus(trip.getTripId(),4);
        EarningsHistory earning = new EarningsHistory();
        double distance = distance(trip.getStartLatitude(), trip.getStartLongitude(), trip.getEndLatitude(),
                trip.getEndLongitude());
        earning.setTripId(trip.getTripId());
        earning.setDriverId(trip.getDriverId());
        earning.setCost(((int)(distance*40)));
        earning.setDistanceTravelled((float) distance);
        earningsHistoryService.saveEarning(earning);
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}
