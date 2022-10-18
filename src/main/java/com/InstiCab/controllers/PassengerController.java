package com.InstiCab.controllers;

import com.InstiCab.models.Trip;
import com.InstiCab.service.*;
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

    @Autowired
    public PassengerController(UserService userService, DriverService driverService,
                               RegistrationRequestService registrationRequestService,TripService tripService,
                               PassengerService passengerService){
        super(userService,driverService,registrationRequestService);
        this.tripService = tripService;
        this.passengerService = passengerService;
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
        Trip trip = new Trip();
        model.addAttribute("trip",trip);
        return "newTrip";
    }

    @PostMapping("/passenger/newTrip")
    public String createTrip(@ModelAttribute("trip") Trip trip,Model model, RedirectAttributes redirectAttributes) throws Exception {
        trip.setPassengerId(passengerService.getLoggedInPassengerId());
        trip.setStatus(0);
        tripService.saveTrip(trip);
        return "redirect:/passenger/newTripStatus";
    }

    @GetMapping("/passenger/newTripStatus")
    public String showTripStatus(Model model,RedirectAttributes redirectAttributes){
        Long passengerId = passengerService.getLoggedInPassengerId();
        model.addAttribute("passenger",passengerService.get);
        return "newTripStatus";
    }
}
