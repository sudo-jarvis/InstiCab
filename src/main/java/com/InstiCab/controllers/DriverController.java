package com.InstiCab.controllers;

import com.InstiCab.models.Passenger;
import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.models.Transaction;
import com.InstiCab.models.Trip;
import com.InstiCab.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class DriverController extends BaseController{

    private TripService tripService;
    private PassengerService passengerService;
    private TransactionService transactionService;
    @Autowired
    public DriverController(UserService userService, DriverService driverService,
                            RegistrationRequestService registrationRequestService, TripService tripService, PassengerService passengerService, TransactionService transactionService) {
        super(userService,driverService,registrationRequestService);
        this.tripService = tripService;
        this.passengerService = passengerService;
        this.transactionService = transactionService;
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
        List<Trip> tripReqList = tripService.getTripReqList();
        List<Trip> tripList = tripService.getTripList();
        model.addAttribute("driver",driverService.getDriverByDriverId(driverId));
        model.addAttribute("tripReqList", tripReqList);
        model.addAttribute("tripList", tripList);
        System.out.println(tripReqList.size());
        return "driver";
    }

    @PostMapping("/driver/accept/{tripId}")
    public String acceptTripRequest(@PathVariable("tripId") Long tripId, Model model, RedirectAttributes redirectAttributes) throws Exception {
        if(tripService.tripAlreadyRunning()) {
            redirectAttributes.addFlashAttribute("errorMsg", "Trip Already running");
            return "redirect:/driver";
        }
        Long driverId = driverService.findLoggedInDriver();
        Trip tripReq = tripService.getTripByTripId(tripId);
        tripReq.setStartDate(Date.valueOf(LocalDate.now()));
        tripReq.setStartTime(Time.valueOf(LocalTime.now()));
        tripReq.setDriverId(driverId);
        System.out.println("::");
        tripService.acceptTripRequest(tripReq);
        return "redirect:/driver";
    }

    @PostMapping("/driver/reject/{tripId}")
    public String rejectTripRequest(@PathVariable("tripId") Long tripId, Model model){
        tripService.rejectTripRequest(tripId);
        return "redirect:/driver";
    }

    @PostMapping("/driver/end/{tripId}")
    public String endTrip(@PathVariable("tripId") Long tripId, Model model) throws Exception {
        Trip trip = tripService.getTripByTripId(tripId);
        trip.setEndDate(Date.valueOf(LocalDate.now()));
        trip.setEndTime(Time.valueOf(LocalTime.now()));
        trip.setEndLatitude(trip.getEndLatitude());
        trip.setEndLongitude(trip.getEndLongitude());

        Transaction transaction = new Transaction();
        Long passengerId = trip.getPassengerId();
        Passenger passenger = passengerService.getPassengerByPassengerId(passengerId);
        transaction.setUsername(passenger.getUsername());
        transaction.setAmount(70);
        transaction.setStatus(0);
        transaction.setDateTranscation(Date.valueOf(LocalDate.now()));
        transaction.setTimeTransaction(Time.valueOf(LocalTime.now()));
        transactionService.saveTransaction(transaction);
        tripService.endTrip(trip);
        return "redirect:/driver";
    }
}
