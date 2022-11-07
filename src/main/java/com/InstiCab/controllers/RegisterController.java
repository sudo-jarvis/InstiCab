package com.InstiCab.controllers;

import com.InstiCab.models.*;
import com.InstiCab.service.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class RegisterController extends BaseController {

    private PassengerService passengerService;
    private VehicleService vehicleService;
    private FavouriteLocationService favouriteLocationService;
    @Getter
    @Setter
    static class DriverDetails {
        private User user;
        private Vehicle vehicle;
        private Driver driver;
    }
    @Autowired
    public RegisterController(UserService userService, DriverService driverService,RegistrationRequestService registrationRequestService,PassengerService passengerService
            ,VehicleService vehicleService,FavouriteLocationService favouriteLocationService) {
        super(userService,driverService,registrationRequestService);
        this.passengerService = passengerService;
        this.vehicleService = vehicleService;
        this.favouriteLocationService = favouriteLocationService;
    }

    @GetMapping("/register/driver")
    public String registerDriver(Model model) {
        DriverDetails driverDetails = new DriverDetails();
        driverDetails.setDriver(new Driver());
        driverDetails.setUser(new User());
        driverDetails.setVehicle(new Vehicle());
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
        redirectAttributes.addFlashAttribute("errorMsg", "Error Occured. Kindly enter valid details");
        return "redirect:/login/";
    }

    @PostMapping("/register/driver")
    public String registerDriverManager(@ModelAttribute("details") DriverDetails driverDetails,
                                        Model model, RedirectAttributes redirectAttributes) {
        User user = driverDetails.getUser();
        Driver driver = driverDetails.getDriver();
        Vehicle vehicle = driverDetails.getVehicle();
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
        vehicle.setDriverId(driver.getDriverId());

        vehicleService.saveVehicle(vehicle);
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
        Long passengerId = passengerService.getLoggedPassengerIdByUsername(user.getUsername());
        favouriteLocationService.addDefaultLocation(passengerId);
        redirectAttributes.addFlashAttribute("successMsg",
                "Registered successfully!");
        return "redirect:/login";
    }
}
