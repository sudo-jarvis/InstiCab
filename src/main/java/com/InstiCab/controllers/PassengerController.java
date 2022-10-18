package com.InstiCab.controllers;

import com.InstiCab.models.Trip;
import com.InstiCab.service.DriverService;
import com.InstiCab.service.RegistrationRequestService;
import com.InstiCab.service.TripService;
import com.InstiCab.service.UserService;
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

    @Autowired
    public PassengerController(UserService userService, DriverService driverService,
                               RegistrationRequestService registrationRequestService,TripService tripService){
        super(userService,driverService,registrationRequestService);
        this.tripService = tripService;
    }

    @GetMapping("/passenger/newtrip")
    public String newTrip(Model model){
        if(!isLoggedIn()){
            return "redirect:/";
        }
        Trip trip = new Trip();

        return "newtrip";
    }

    @PostMapping("/passenger/newtrip")
    public String createTrip(@ModelAttribute("trip") Trip trip,Model model, RedirectAttributes redirectAttributes){

        //tripService.saveTrip(trip);
        return "redirect:/newTripStatus";
    }
}
