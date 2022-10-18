package com.InstiCab.controllers;

import com.InstiCab.models.*;
import com.InstiCab.service.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class RegisterController extends BaseController {

    private PassengerService passengerService;
    @Getter
    @Setter
    static class DriverDetails {
        private User user;
        private Driver driver;
    }
    @Autowired
    public RegisterController(UserService userService, DriverService driverService,
                              RegistrationRequestService registrationRequestService, PassengerService passengerService) {
        super(userService,driverService,registrationRequestService);
        this.passengerService = passengerService;
    }

    @GetMapping("/register/driver")
    public String registerDriver(Model model) {
        DriverDetails driverDetails = new DriverDetails();
        driverDetails.setDriver(new Driver());
        driverDetails.setUser(new User());

        if (isLoggedIn()) {
            return "redirect:/";
        }
        model.addAttribute("details", driverDetails);
        return "register_driver";
    }
    @GetMapping("/register/passenger")
    public String registerPassenger(Model model) {
        if (isLoggedIn()) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "register_passenger";
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public String handleDuplicateKeyException(Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMsg", "Username not available!");
        return "redirect:/login/";
    }

    @PostMapping("/register/driver")
    public String registerDriverManager(@ModelAttribute("details") DriverDetails driverDetails,
                                        Model model, RedirectAttributes redirectAttributes) {
        User user = driverDetails.getUser();
        Driver driver = driverDetails.getDriver();

        if (isLoggedIn()) {
            return "redirect:/";
        }
        driver.setUsername(user.getUsername());
        user.setDateCreated(Date.valueOf(LocalDate.now()));
        user.setLastLoginTime(Time.valueOf(LocalTime.now()));
        user.setLastLoginDate(Date.valueOf(LocalDate.now()));
        user.setRole(ROLE_DRIVER);

        RegistrationRequest userRequest = new RegistrationRequest();
        userRequest.setTimeApplied(user.getLastLoginTime());
        userRequest.setDateApplied(user.getDateCreated());
        userRequest.setStatus(0);

        userService.saveUser(user);
        driverService.saveDriver(driver);

        driver = driverService.getDriverByUsername(user.getUsername());
        userRequest.setDriverId(driver.getDriverId());

        registrationRequestService.createRegistrationRequest(userRequest);

        redirectAttributes.addFlashAttribute("successMsg",
                "Registered successfully!");
        return "redirect:/login";
    }

    @PostMapping("/register/passenger")
    public String registerPassengerManager(@ModelAttribute("user") User user, Model model,
                                        RedirectAttributes redirectAttributes) {
        user.setDateCreated(Date.valueOf(LocalDate.now()));
        user.setLastLoginTime(Time.valueOf(LocalTime.now()));
        user.setLastLoginDate(Date.valueOf(LocalDate.now()));
        user.setRole(ROLE_PASSENGER);
        if (isLoggedIn()) {
            return "redirect:/";
        }
        userService.saveUser(user);
        passengerService.savePassenger(user.getUsername());

        redirectAttributes.addFlashAttribute("successMsg",
                "Registered successfully!");
        return "redirect:/login";
    }
}
