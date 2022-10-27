package com.InstiCab.controllers;

import com.InstiCab.models.*;
import com.InstiCab.service.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PassengerController extends BaseController{

    private TripService tripService;
    private PassengerService passengerService;
    private FavouriteLocationService favouriteLocationService;

    @Getter
    @Setter
    static class TripDetails {
        private FavouriteLocation favouriteLocation;
        private Trip trip;
    }

    @Autowired
    public PassengerController(UserService userService, DriverService driverService,
                               RegistrationRequestService registrationRequestService,TripService tripService,
                               PassengerService passengerService, FavouriteLocationService favouriteLocationService){
        super(userService,driverService,registrationRequestService);
        this.tripService = tripService;
        this.passengerService = passengerService;
        this.favouriteLocationService = favouriteLocationService;
    }

    @GetMapping("/passenger/newTrip")
    public String newTrip(Model model,RedirectAttributes redirectAttributes){
        if(!isLoggedIn()){
            return "redirect:/";
        }
        if(tripService.tripAlreadyExists()){
            redirectAttributes.addFlashAttribute("errorMsg", "Pending Trip Already Exists !");
            return "redirect:/passenger/newTripStatus";
        }
        TripDetails tripDetails = new TripDetails();
        tripDetails.setFavouriteLocation(new FavouriteLocation());
        tripDetails.setTrip(new Trip());
        model.addAttribute("tripDetails",tripDetails);
        return "newTrip";
    }

    @PostMapping("/passenger/newTrip")
    public String createTrip(@ModelAttribute("tripDetails") TripDetails tripDetails,Model model, RedirectAttributes redirectAttributes) throws Exception {
        FavouriteLocation favouriteLocation = tripDetails.getFavouriteLocation();
        Trip trip = tripDetails.getTrip();
        Long passengerId = passengerService.getLoggedInPassengerId();
        trip.setPassengerId(passengerId);
        trip.setStatus(0);
        tripService.saveTrip(trip);

//        favouriteLocation.setLatitudeLocation(trip.getEndLatitude());
//        favouriteLocation.setLongitudeLocation(trip.getEndLongitude());
//        favouriteLocation.setPassengerId(passengerId);
//        favouriteLocationService.saveFavouriteLocation(favouriteLocation);
        return "redirect:/passenger/newTripStatus";
    }

    @GetMapping("/passenger/newTripStatus")
    public String showTripStatus(Model model,RedirectAttributes redirectAttributes){
        Long passengerId = passengerService.getLoggedInPassengerId();
        model.addAttribute("passengerTripList",tripService.getPassengerAllTrips(passengerId));
        return "newTripStatus";
    }
}
