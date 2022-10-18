package com.InstiCab.controllers;

import com.InstiCab.service.DriverService;
import com.InstiCab.service.RegistrationRequestService;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController extends BaseController{


    @Autowired
    public HomeController(UserService userService, DriverService driverService,
                          RegistrationRequestService registrationRequestService) {
        super(userService,driverService,registrationRequestService);
    }

    @GetMapping({ "/", "" })
    public String home(Model model, RedirectAttributes redirectAttributes) {
        if (isLoggedIn()) {
            if (isAuthorized(model, ROLE_ADMIN))
                return "redirect:/admin";

            if(isAuthorized(model,ROLE_DRIVER)) {
                if(!isVerified()){
                    redirectAttributes.addFlashAttribute("errorMsg", "Not Verified ! !");
                    return "redirect:/logout";
                }
//                System.out.println("hi");;
                return "redirect:/driver";
            }
            model.addAttribute("isPassenger",true);
            return "home";
        }
        return "redirect:/login/";
    }
}
