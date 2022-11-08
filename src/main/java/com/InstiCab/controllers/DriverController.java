package com.InstiCab.controllers;

import com.InstiCab.models.*;
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
    private EarningsHistoryService earningsHistoryService;
    private EmergencyService emergencyService;
    @Autowired
    public DriverController(UserService userService, DriverService driverService,
                            RegistrationRequestService registrationRequestService, TripService tripService, PassengerService passengerService, TransactionService transactionService, EarningsHistoryService earningsHistoryService, EmergencyService emergencyService) {
        super(userService,driverService,registrationRequestService);
        this.tripService = tripService;
        this.passengerService = passengerService;
        this.transactionService = transactionService;
        this.earningsHistoryService = earningsHistoryService;
        this.emergencyService = emergencyService;
    }

    public double distance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;
            return (dist);
        }
    }

    @GetMapping("/driver")
    public String driverhomepage(Model model) throws Exception {
        if(!isLoggedIn()) {
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
        return "driver";
    }

    @GetMapping("/driver/accept/{tripId}")
    public String invalidPage(Model model){
        if(!isLoggedIn() || !isAuthorized(model,ROLE_DRIVER))
            return FORBIDDEN_ERROR_PAGE;
        return "redirect:/";
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

    @GetMapping("/driver/reject/{tripId}")
    public String invalidPage2(Model model){
        if(!isLoggedIn() || !isAuthorized(model,ROLE_DRIVER))
            return FORBIDDEN_ERROR_PAGE;
        return "redirect:/";
    }

    @PostMapping("/driver/reject/{tripId}")
    public String rejectTripRequest(@PathVariable("tripId") Long tripId, Model model){
        tripService.rejectTripRequest(tripId);
        return "redirect:/driver";
    }

    @GetMapping("/driver/end/{tripId}")
    public String invalidPage3(Model model){
        if(!isLoggedIn() || !isAuthorized(model,ROLE_DRIVER))
            return FORBIDDEN_ERROR_PAGE;
        return "redirect:/";
    }

    @PostMapping("/driver/end/{tripId}")
    public String endTrip(@PathVariable("tripId") Long tripId, Model model) throws Exception {
        Trip trip = tripService.getTripByTripId(tripId);
        trip.setEndDate(Date.valueOf(LocalDate.now()));
        trip.setEndTime(Time.valueOf(LocalTime.now()));
        trip.setEndLatitude(trip.getEndLatitude());
        trip.setEndLongitude(trip.getEndLongitude());

        double distance = distance(trip.getStartLatitude(), trip.getStartLongitude(), trip.getEndLatitude(), trip.getEndLongitude());

        Transaction transaction = new Transaction();
        Long passengerId = trip.getPassengerId();
        Passenger passenger = passengerService.getPassengerByPassengerId(passengerId);
        transaction.setUsername(passenger.getUsername());
        transaction.setAmount((int) (distance*40));
        transaction.setTripId(trip.getTripId());
        transaction.setStatus(0);

        transactionService.saveTransaction(transaction);
        tripService.changeTripStatus(trip.getTripId(),3);
        return "redirect:/driver";
    }

    @GetMapping("/driver/showEarningHistory")
    public String showEarningHistory(Model model,RedirectAttributes redirectAttributes){
        if(!isLoggedIn() || !isAuthorized(model,ROLE_DRIVER))
            return FORBIDDEN_ERROR_PAGE;
        Long driverId = driverService.findLoggedInDriver();
        model.addAttribute("earningHistory",earningsHistoryService.getEarningHistory(driverId));
        return "earning_history";
    }


    @GetMapping("/driver/showTrips")
    public String showTrips(Model model,RedirectAttributes redirectAttributes) throws Exception {
        if(!isLoggedIn() || !isAuthorized(model,ROLE_DRIVER))
            return FORBIDDEN_ERROR_PAGE;
        Long driverId = driverService.findLoggedInDriver();
        model.addAttribute("trips",tripService.getDriverAllTrips(driverId));
        return "currentTrips";
    }        

    @GetMapping("/driver/profile")
    public String driverProfile(Model model) {
        if (!isLoggedIn() || !isAuthorized(model, ROLE_DRIVER))
            return FORBIDDEN_ERROR_PAGE;
        Long driverId = driverService.findLoggedInDriver();
        Driver driver = driverService.getDriverByDriverId(driverId);
        String username = userService.findLoggedInUsername();
        User user = userService.getUserByUsername(username);
        model.addAttribute("driver", driver);
        model.addAttribute("user", user);
        return "driver_profile";
    }


    @GetMapping("/driver/EmergencyRequest")
    public String NewEmergencyRequest(Model model){
        if(!isLoggedIn() || isAuthorized(model,ROLE_ADMIN)){
            return FORBIDDEN_ERROR_PAGE;
        }
        return "emergencyServices";
    }

    @PostMapping("/driver/EmergencyRequest/hospital")
    public String CreateHospitalRequest(Model model){
        if(!isLoggedIn() || isAuthorized(model,ROLE_ADMIN)){
            return FORBIDDEN_ERROR_PAGE;
        }
        emergencyService.createHospitalRequest();
        return "redirect:/";
    }

    @PostMapping("/driver/EmergencyRequest/police")
    public String CreatePoliceRequest(Model model){
        if(!isLoggedIn() || isAuthorized(model,ROLE_ADMIN)){
            return FORBIDDEN_ERROR_PAGE;
        }
        emergencyService.createPoliceRequest();
        return "redirect:/";
    }

    @PostMapping("/driver/EmergencyRequest/fire")
    public String CreateFireStationRequest(Model model){
        if(!isLoggedIn() || isAuthorized(model,ROLE_ADMIN)){
            return FORBIDDEN_ERROR_PAGE;
        }
        emergencyService.createFireStationRequest();
        return "redirect:/";
    }
    @GetMapping("/driver/previousTrips")
    public String showPreviousRequest(Model model,RedirectAttributes redirectAttributes){
        if(!isLoggedIn() || !isAuthorized(model,ROLE_DRIVER))
            return FORBIDDEN_ERROR_PAGE;
        Long driverId = driverService.findLoggedInDriver();
        model.addAttribute("earningHistory",earningsHistoryService.getEarningHistory(driverId));
        return "earning_history";

    }
}
