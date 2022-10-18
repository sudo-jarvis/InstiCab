package com.InstiCab.controllers;

import com.InstiCab.service.DriverService;
import com.InstiCab.service.PassengerService;
import com.InstiCab.service.RegistrationRequestService;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DriverController extends BaseController{

    @Autowired
    public DriverController(UserService userService, DriverService driverService,
                            RegistrationRequestService registrationRequestService, PassengerService passengerService) {
        super(userService,driverService,registrationRequestService,passengerService);
    }

    @GetMapping("/driver")
    public String driverhomepage(Model model) {
        if(!isLoggedIn()) {
            if (isAuthorized(model, ROLE_ADMIN))
                model.addAttribute("isAdmin", true);
            return "redirect:/login/";
        }
        if(!isAuthorized(model,ROLE_DRIVER))
            return FORBIDDEN_ERROR_PAGE;
        Long driverId = driverService.findLoggedInDriver();
        model.addAttribute("driver",driverService.getDriverByDriverId(driverId));
        return "driver";
    }
}
