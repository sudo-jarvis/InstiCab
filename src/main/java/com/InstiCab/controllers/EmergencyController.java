package com.InstiCab.controllers;

import com.InstiCab.service.DriverService;
import com.InstiCab.service.EmergencyService;
import com.InstiCab.service.RegistrationRequestService;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmergencyController extends BaseController {

    @Autowired
    EmergencyService emergencyService;

    public EmergencyController(UserService userService, DriverService driverService,
                                     RegistrationRequestService registrationRequestService){
        super(userService,driverService,registrationRequestService);

    }

    @GetMapping("/EmergencyRequest")
    public String NewEmergencyRequest(Model model,RedirectAttributes redirectAttributes){
        if(!isLoggedIn() || isAuthorized(model,ROLE_ADMIN)){
            return FORBIDDEN_ERROR_PAGE;
        }
        return "emergencyServices";
    }

    @PostMapping("/EmergencyRequest/hospital")
    public String CreateHospitalRequest(Model model, RedirectAttributes redirectAttributes){
        if(!isLoggedIn() || isAuthorized(model,ROLE_ADMIN)){
            redirectAttributes.addFlashAttribute("errorMsg","Proper Authorization Required !");
            return FORBIDDEN_ERROR_PAGE;
        }
        redirectAttributes.addFlashAttribute("successMsg","Emergency Request Succesfully Sent !");
        emergencyService.createHospitalRequest();
        return "redirect:/";
    }

    @PostMapping("/EmergencyRequest/police")
    public String CreatePoliceRequest(Model model,RedirectAttributes redirectAttributes){
        if(!isLoggedIn() || isAuthorized(model,ROLE_ADMIN)){
            redirectAttributes.addFlashAttribute("errorMsg","Proper Authorization Required !");
            return FORBIDDEN_ERROR_PAGE;
        }
        redirectAttributes.addFlashAttribute("successMsg","Emergency Request Succesfully Sent !");
        emergencyService.createPoliceRequest();
        return "redirect:/";
    }

    @PostMapping("/EmergencyRequest/fire")
    public String CreateFireStationRequest(Model model,RedirectAttributes redirectAttributes){
        if(!isLoggedIn() || isAuthorized(model,ROLE_ADMIN)){
            redirectAttributes.addFlashAttribute("errorMsg","Proper Authorization Required !");
            return FORBIDDEN_ERROR_PAGE;
        }
        redirectAttributes.addFlashAttribute("successMsg","Emergency Request Succesfully Sent !");
        emergencyService.createFireStationRequest();
        return "redirect:/";

    }

    @GetMapping("/admin/ViewEmergencyRequest")
    public String ViewEmergencyRequests(Model model,RedirectAttributes redirectAttributes){
        if(!isAuthorized(model,ROLE_ADMIN)){
            return FORBIDDEN_ERROR_PAGE;
        }
        model.addAttribute("requests",emergencyService.getEmergencyRequests());
        return "view_emergency_request";
    }
}
