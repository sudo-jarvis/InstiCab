package com.InstiCab.controllers;

import com.InstiCab.models.*;
import com.InstiCab.service.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.text.ParseException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
public class PassengerController extends BaseController{

    private TripService tripService;
    private ScheduledTripService scheduledTripService;
    private PassengerService passengerService;
    private FavouriteLocationService favouriteLocationService;
    private TransactionService transactionService;
    private CouponService couponService;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
   static class TripDetails {
        private FavlocationJoinLocation favouriteLocation;
        private List<FavlocationJoinLocation> favouriteLocationsList;
        private Trip trip;
        String scheduledTime;
    }

    @Autowired
    public PassengerController(UserService userService, DriverService driverService,
                               RegistrationRequestService registrationRequestService,TripService tripService,
                               PassengerService passengerService, FavouriteLocationService favouriteLocationService,
                               TransactionService transactionService,ScheduledTripService scheduledTripService, CouponService couponService){
        super(userService,driverService,registrationRequestService);
        this.tripService = tripService;
        this.passengerService = passengerService;
        this.favouriteLocationService = favouriteLocationService;
        this.transactionService = transactionService;
        this.couponService = couponService;
        this.scheduledTripService = scheduledTripService;
    }

    @GetMapping("/passenger/newTrip")
    public String newTrip(Model model,RedirectAttributes redirectAttributes){
        if(!isLoggedIn()){
            return "redirect:/";
        }
        if(!isAuthorized(model,ROLE_PASSENGER)) return FORBIDDEN_ERROR_PAGE;
        if(tripService.tripAlreadyExists()){
            redirectAttributes.addFlashAttribute("errorMsg", "Pending Trip Already Exists !");
            return "redirect:/passenger/newTripStatus";
        }
        if(transactionService.transactionPending()){
            redirectAttributes.addFlashAttribute("errorMsg", "Pending Transaction Exists !");
            return "redirect:/passenger/transaction";
        }
        TripDetails tripDetails = new TripDetails();
        tripDetails.setTrip(new Trip());
        tripDetails.setFavouriteLocation(new FavlocationJoinLocation());
        tripDetails.setFavouriteLocationsList(favouriteLocationService.getFavLocations(passengerService.getLoggedInPassengerId()));
        model.addAttribute("tripDetails", tripDetails);
        return "newTrip";
    }

    @GetMapping("/passenger/newScheduledTrip")
    public String newScheduledTrip(Model model,RedirectAttributes redirectAttributes){
        if(!isLoggedIn()){
            return "redirect:/";
        }
        if(tripService.tripAlreadyExists()){
            redirectAttributes.addFlashAttribute("errorMsg", "Pending Trip Already Exists !");
            return "redirect:/passenger/newTripStatus";
        }
        if(transactionService.transactionPending()){
            redirectAttributes.addFlashAttribute("errorMsg", "Pending Transaction Exists !");
            return "redirect:/passenger/transaction";
        }
        TripDetails tripDetails = new TripDetails();
        tripDetails.setTrip(new Trip());
        tripDetails.setFavouriteLocation(new FavlocationJoinLocation());
        tripDetails.setFavouriteLocationsList(favouriteLocationService.getFavLocations(passengerService.getLoggedInPassengerId()));
        model.addAttribute("tripDetails", tripDetails);
        return "newScheduledTrip";
    }

    @PostMapping("/passenger/newTrip")
    public String createTrip(@RequestParam(name = "favLocLabel", defaultValue = "", required = false) String label,
                             @RequestParam(name =
            "addFavouriteLocation", defaultValue = "false") Boolean addFavouriteLocation, @ModelAttribute("tripDetails") TripDetails tripDetails, Model model, RedirectAttributes redirectAttributes) throws Exception {
        Trip trip = tripDetails.getTrip();
        if(!tripService.checkValidTrip(trip)) {
            redirectAttributes.addFlashAttribute("errorMsg", "Invalid Location!");
            return "redirect:/passenger/newTripStatus";
        }
        Long passengerId = passengerService.getLoggedInPassengerId();
        trip.setPassengerId(passengerId);
        trip.setStatus(0);
        tripService.saveTrip(trip);

        if(addFavouriteLocation){
            FavouriteLocation favLoc = new FavouriteLocation();
            Location location=new Location();
            location.setLatitudeLocation(trip.getEndLatitude());
            location.setLongitudeLocation(trip.getEndLongitude());
            location = (favouriteLocationService.saveLocation(location));
            favLoc.setPassengerId(passengerId);
            favLoc.setLabel(label);
            favLoc.setLocationId(location.getLocationId());
            favouriteLocationService.saveFavouriteLocation(favLoc);
        }
        return "redirect:/passenger/newTripStatus";
    }

    @PostMapping("/passenger/newScheduledTrip")
    public String createScheduledTrip(@RequestParam(name = "favLocLabel", defaultValue = "", required = false) String label,@RequestParam(name = "addFavouriteLocation", defaultValue = "false") Boolean addFavouriteLocation, @ModelAttribute("tripDetails") TripDetails tripDetails, Model model, RedirectAttributes redirectAttributes) throws Exception {
        Trip trip = tripDetails.getTrip();
        if(!tripService.checkValidTrip(trip)) {
            redirectAttributes.addFlashAttribute("errorMsg", "Invalid Location!");
            return "redirect:/passenger/newTripStatus";
        }
        Long passengerId = passengerService.getLoggedInPassengerId();
        trip.setPassengerId(passengerId);
        trip.setStatus(5);
        String time = tripDetails.getScheduledTime();
        time = time + ":00";
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat2.format(new Date());
        String dateTime = today + " " + time;
        Date d = dateFormat1.parse(dateTime);
        tripService.saveTrip(trip);
        if(d.compareTo(java.sql.Date.valueOf(LocalDate.now()))<0){
            redirectAttributes.addFlashAttribute("errorMsg","Give Future Time ! !");
            return FORBIDDEN_ERROR_PAGE;
        }
        trip = tripService.getScheduledTrip(passengerService.getLoggedInPassengerId());
        ScheduledTrip scheduledTrip = new ScheduledTrip();
        scheduledTrip.setTripId(trip.getTripId());
        scheduledTrip.setTripTime(new Time(d.getTime()));
        scheduledTripService.saveScheduledTrip(scheduledTrip);
        Timer timer = new Timer();
        Trip finalTrip = trip;
        TimerTask scheduleTripInsert = new TimerTask(){
            final Date date = d;
            final Trip t = finalTrip;
            @Override
            public void run(){
                tripService.changeTripStatus(t.getTripId(),0);
                try {
                    scheduledTripService.delete(scheduledTrip);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule(scheduleTripInsert, d);

        if(addFavouriteLocation){
            FavouriteLocation favLoc = new FavouriteLocation();
            Location location=new Location();
            location.setLatitudeLocation(trip.getEndLatitude());
            location.setLongitudeLocation(trip.getEndLongitude());
            location = (favouriteLocationService.saveLocation(location));
            favLoc.setPassengerId(passengerId);
            favLoc.setLabel(label);
            favLoc.setLocationId(location.getLocationId());
            favouriteLocationService.saveFavouriteLocation(favLoc);
        }
        return "redirect:/passenger/newTripStatus";
    }

    @GetMapping("/passenger/newTripStatus")
    public String showTripStatus(Model model,RedirectAttributes redirectAttributes){
        if(!isLoggedIn()){
            return "redirect:/";
        }
        if(!isAuthorized(model,ROLE_PASSENGER)) return FORBIDDEN_ERROR_PAGE;
        Long passengerId = passengerService.getLoggedInPassengerId();
        model.addAttribute("passengerTripList",tripService.getPassengerAllTrips(passengerId));
        return "newTripStatus";
    }

    @GetMapping("/passenger/transaction")
    public String showTransaction(Model model,RedirectAttributes redirectAttributes){
        if(!isLoggedIn()){
            return "redirect:/";
        }
        if(!isAuthorized(model,ROLE_PASSENGER)) return FORBIDDEN_ERROR_PAGE;
        Long passengerId = passengerService.getLoggedInPassengerId();
        Passenger passenger = passengerService.getPassengerByPassengerId(passengerId);
        TransactionDispute transactionDispute = new TransactionDispute();
        model.addAttribute("transactionList",transactionService.getPassengerAllTransactions(passenger.getUsername()));
        model.addAttribute("dispute",transactionDispute);
        return "transaction";
    }

    @PostMapping("/feedback")
    public String feedback(@RequestParam(name = "tripId") String tripId, Model model) throws Exception {
        if(!isLoggedIn() || !isAuthorized(model,ROLE_PASSENGER)){
            return FORBIDDEN_ERROR_PAGE;
        }
        model.addAttribute("tripId", tripId);
        return "feedback";
    }

    @PostMapping("/saveFeedback")
    public String saveFeedback(@RequestParam(name = "feedback") String feedback, @RequestParam(name = "tripId") String tripId, Model model) throws Exception {
        if(!isLoggedIn() || !isAuthorized(model,ROLE_PASSENGER)){
            return FORBIDDEN_ERROR_PAGE;
        }
        tripService.saveFeedback(feedback, Long.valueOf(tripId));
        return "redirect:/";
    }

    @PostMapping("/endTransaction")
    public String endTransaction(Model model,RedirectAttributes redirectAttributes) throws Exception {
        if(!isLoggedIn() || !isAuthorized(model,ROLE_PASSENGER)){
            return FORBIDDEN_ERROR_PAGE;
        }
        Long passengerId = passengerService.getLoggedInPassengerId();
        Passenger passenger = passengerService.getPassengerByPassengerId(passengerId);
        transactionService.endTransaction(passenger.getUsername());
        return "redirect:/";
    }

    @PostMapping("/passenger/coupon")
    public String useCoupon(@RequestParam(name = "amountToPay") Integer amountToPay, @RequestParam(name =
            "transactionId") String transactionId, @RequestParam(name =
            "tripId") String tripId, Model model,RedirectAttributes redirectAttributes) throws ParseException {

        if(!isLoggedIn() || !isAuthorized(model,ROLE_PASSENGER)){
            return FORBIDDEN_ERROR_PAGE;
        }
        Long passengerId = passengerService.getLoggedInPassengerId();
        List<Coupon>allCoupons = couponService.getPassengerAllCoupons(passengerId);
        List<Coupon> availableCoupons = new ArrayList<>();
        for (Coupon coupon : allCoupons) {
            Date d1 = coupon.getCouponValidity();
            Date d2 = java.sql.Date.valueOf(LocalDate.now());
            if (d1.compareTo(d2) >= 0) {
                coupon.setCouponDiscount(amountToPay - coupon.getCouponDiscount());
                availableCoupons.add(coupon);
            }
        }
        model.addAttribute("couponList",availableCoupons);
        model.addAttribute("transactionId",transactionId);
        model.addAttribute("tripId",tripId);
        return "coupon";
    }

    @GetMapping("/passenger/cancel/{tripId}")
    public String invalidPage(Model model){
        return FORBIDDEN_ERROR_PAGE;
    }

    @PostMapping("/passenger/cancel/{tripId}")
    public String cancelTrip(@PathVariable("tripId") Long tripId, Model model,RedirectAttributes redirectAttributes) throws Exception {
        if(!isLoggedIn() || !isAuthorized(model,ROLE_PASSENGER)){
            return FORBIDDEN_ERROR_PAGE;
        }
        tripService.cancelTrip(tripId);
        return "redirect:/";
    }

    @GetMapping("/passenger/profile")
    public String passengerProfile(Model model) {
        if(!isLoggedIn() || !isAuthorized(model,ROLE_PASSENGER))
            return FORBIDDEN_ERROR_PAGE;
        String username = userService.findLoggedInUsername();
        User user = userService.getUserByUsername(username);
        model.addAttribute("user",user);
        return "passenger_profile";
    }
}
