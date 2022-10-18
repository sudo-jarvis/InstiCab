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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DriverController extends BaseController{

    private TripService tripService;
    @Autowired
    public DriverController(UserService userService, DriverService driverService,
                            RegistrationRequestService registrationRequestService, TripService tripService) {
        super(userService,driverService,registrationRequestService);
        this.tripService = tripService;
    }

    @GetMapping("/driver")
    public String driverhomepage(Model model) throws Exception {
        if(!isLoggedIn()) {
            if (isAuthorized(model, ROLE_ADMIN))
                model.addAttribute("isAdmin", true);
            return "redirect:/login/";
        }
        if(!isAuthorized(model,ROLE_DRIVER))
            return FORBIDDEN_ERROR_PAGE;
        Long driverId = driverService.findLoggedInDriver();
        List<Trip> tripList = tripService.getTripList();
        model.addAttribute("driver",driverService.getDriverByDriverId(driverId));
        model.addAttribute("tripList", tripList);
        System.out.println(tripList.size());
        return "driver";
    }
}
