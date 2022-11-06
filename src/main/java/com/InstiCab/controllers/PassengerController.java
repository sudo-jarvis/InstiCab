package com.InstiCab.controllers;

import com.InstiCab.models.*;
import com.InstiCab.service.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PassengerController extends BaseController{

    private TripService tripService;
    private PassengerService passengerService;
    private FavouriteLocationService favouriteLocationService;
    private TransactionService transactionService;
    private CouponService couponService;

    @Getter
    @Setter
    static class TripDetails {
        private FavouriteLocation favouriteLocation;
        private Trip trip;
    }

    @Autowired
    public PassengerController(UserService userService, DriverService driverService,
                               RegistrationRequestService registrationRequestService,TripService tripService,
                               PassengerService passengerService, FavouriteLocationService favouriteLocationService, TransactionService transactionService, CouponService couponService){
        super(userService,driverService,registrationRequestService);
        this.tripService = tripService;
        this.passengerService = passengerService;
        this.favouriteLocationService = favouriteLocationService;
        this.transactionService = transactionService;
        this.couponService = couponService;
    }

    @GetMapping("/passenger/newTrip")
    public String newTrip(Model model,RedirectAttributes redirectAttributes){
        TripDetails tripDetails = new TripDetails();
        tripDetails.setTrip(new Trip());

        if(!isLoggedIn()){
            return "redirect:/";
        }
        if(tripService.tripAlreadyExists()){
            redirectAttributes.addFlashAttribute("errorMsg", "Pending Trip Already Exists !");
            return "redirect:/passenger/newTripStatus";
        }

        if(transactionService.transactionPending()){
            redirectAttributes.addFlashAttribute("errorMsg", "Pending Transaction Exists !");
            return "redirect:/passenger/transaction";
        }
        model.addAttribute("tripDetails", tripDetails);
        return "newTrip";
    }

    @PostMapping("/passenger/newTrip")
    public String createTrip(@RequestParam(name = "addFavouriteLocation", defaultValue = "false") Boolean addFavouriteLocation, @ModelAttribute("tripDetails") TripDetails tripDetails, Model model, RedirectAttributes redirectAttributes) throws Exception {
        Trip trip = tripDetails.getTrip();
        Long passengerId = passengerService.getLoggedInPassengerId();
        trip.setPassengerId(passengerId);
        trip.setStatus(0);
        tripService.saveTrip(trip);

        if(addFavouriteLocation){
            tripDetails.setFavouriteLocation(new FavouriteLocation());
            FavouriteLocation favouriteLocation = tripDetails.getFavouriteLocation();
            favouriteLocation.setLatitudeLocation(trip.getEndLatitude());
            favouriteLocation.setLongitudeLocation(trip.getEndLongitude());
            favouriteLocation.setPassengerId(passengerId);
            favouriteLocationService.saveFavouriteLocation(favouriteLocation);
        }
        return "redirect:/passenger/newTripStatus";
    }

    @GetMapping("/passenger/newTripStatus")
    public String showTripStatus(Model model,RedirectAttributes redirectAttributes){
        Long passengerId = passengerService.getLoggedInPassengerId();
        model.addAttribute("passengerTripList",tripService.getPassengerAllTrips(passengerId));
        return "newTripStatus";
    }

    @GetMapping("/passenger/transaction")
    public String showTransaction(Model model,RedirectAttributes redirectAttributes){
        Long passengerId = passengerService.getLoggedInPassengerId();
        Passenger passenger = passengerService.getPassengerByPassengerId(passengerId);
        model.addAttribute("transactionList",transactionService.getPassengerAllTransactions(passenger.getUsername()));
        return "transaction";
    }

    @PostMapping("/endTransaction")
    public String endTransaction(Model model,RedirectAttributes redirectAttributes) throws Exception {
        Long passengerId = passengerService.getLoggedInPassengerId();
        Passenger passenger = passengerService.getPassengerByPassengerId(passengerId);
        transactionService.endTransaction(passenger.getUsername());
        return "redirect:/";
    }

    @PostMapping("/passenger/coupon")
    public String useCoupon(@RequestParam(name = "amountToPay") Integer amountToPay, Model model,RedirectAttributes redirectAttributes) throws ParseException {
        Long passengerId = passengerService.getLoggedInPassengerId();
        List<Coupon>allCoupons = couponService.getPassengerAllCoupons(passengerId);
        List<Coupon> availableCoupons = new ArrayList<>();
        for(int i=0; i<allCoupons.size(); i++){
            Coupon coupon = allCoupons.get(i);
            java.util.Date d1 = coupon.getCouponValidity();
            java.util.Date d2 = Date.valueOf(LocalDate.now());
            if(d1.compareTo(d2) >= 0){
                coupon.setCouponDiscount(amountToPay - coupon.getCouponDiscount());
                availableCoupons.add(coupon);
            }
        }
        model.addAttribute("couponList",availableCoupons);
        return "coupon";
    }
}
