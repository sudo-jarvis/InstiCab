package com.InstiCab.controllers;

import com.InstiCab.models.User;
import com.InstiCab.service.DriverService;
import com.InstiCab.service.RegistrationRequestService;
import com.InstiCab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class LoginController extends BaseController {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginController(UserService userService, DriverService driverService,
                           RegistrationRequestService registrationRequestService) {
        super(userService,driverService,registrationRequestService);

        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping("/login")
    public String loginGoto(Model model){
        if(isLoggedIn()){
            return "redirect:/";
        }
        model.addAttribute("user",new User());
        return "login";
    }

    @GetMapping("/loggedin/")
    public String loginManager(Model model, RedirectAttributes redirectAttributes) throws Exception {
        if (!isLoggedIn()) {
            return PAGE_NOT_FOUND_ERROR_PAGE;
        }
        userService.updateLoginDetails(userService.findLoggedInUsername());
        redirectAttributes.addFlashAttribute("successMsg", "Welcome!");
        if(isAuthorized(model,ROLE_ADMIN)) return "redirect:/admin";
        if(isAuthorized(model,ROLE_DRIVER)) {
            if (!isVerified()) {
                redirectAttributes.addFlashAttribute("errorMsg", "Not Verified ! !");
                return "redirect:/logout";
            }
        }
        return "redirect:/";
    }
    @GetMapping("/loggedout/")
    public String logoutManager(Model model, RedirectAttributes redirectAttributes) {
        if (isLoggedIn()) {
            return PAGE_NOT_FOUND_ERROR_PAGE;
        }
        return "redirect:/";
    }

    @GetMapping("/login-error/")
    public String checkLoginManager(Model model, RedirectAttributes redirectAttributes) {
        if (isLoggedIn()) {
            return "redirect:/";
        }
        return "redirect:/login/";
    }
}
