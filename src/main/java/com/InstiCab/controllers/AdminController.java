package com.InstiCab.controllers;

import com.InstiCab.models.*;
import com.InstiCab.service.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController extends BaseController{
    private CouponService couponService;
    private PassengerService passengerService;
    private TransactionDisputeService transactionDisputeService;
    private TransactionService transactionService;
    private TripService tripService;

    @Getter
    @Setter
    static class RequestDetails {
        private Driver driver;
        private RegistrationRequest registrationRequest;
    }

    @Autowired
    public AdminController(UserService userService,
                           DriverService driverService, RegistrationRequestService registrationRequestService,
                           CouponService couponService,TransactionDisputeService transactionDisputeService,
                           TransactionService transactionService,TripService tripService, PassengerService passengerService) {
        super(userService,driverService,registrationRequestService);
        this.couponService = couponService;
        this.transactionDisputeService = transactionDisputeService;
        this.transactionService = transactionService;
        this.tripService = tripService;
        this.passengerService = passengerService;
    }

    @GetMapping({"/admin/", "/admin"})
    public String adminHome(Model model) {
        if(!isLoggedIn()) {
            return "redirect:/";
        }
        if(!isAuthorized(model,ROLE_ADMIN))
            return FORBIDDEN_ERROR_PAGE;
        List<RegistrationRequest>requestList = registrationRequestService.getPendingRequest();
        List<Driver>driverList = driverService.getPendingDrivers();
        List<RequestDetails>requestDetailsList = new ArrayList<>();

        List<User> userList = userService.getAllUsers();

        for(int i = 0; i < requestList.size(); i++) {
            RequestDetails requestDetails = new RequestDetails();
            requestDetails.setRegistrationRequest(requestList.get(i));
            requestDetails.setDriver(driverList.get(i));
            requestDetailsList.add(requestDetails);
        }
        model.addAttribute("requestDetailsList",requestDetailsList);
        model.addAttribute("userList", userList);
        model.addAttribute("isAdmin",true);
        return "admin";
    }

    @GetMapping("/admin/accept/{driverId}")
    public String invalidPage(Model model){
        if(!isLoggedIn() || !isAuthorized(model,ROLE_ADMIN))
            return FORBIDDEN_ERROR_PAGE;
        return "redirect:/";
    }

    @PostMapping("/admin/accept/{driverId}")
    public String acceptRequest(@PathVariable("driverId") Long driverId, Model model){
        if(!isLoggedIn() || !isAuthorized(model,ROLE_ADMIN))
            return FORBIDDEN_ERROR_PAGE;
        RegistrationRequest req = registrationRequestService.getRequestByDriverId(driverId);
        req.setDateAccepted(Date.valueOf(LocalDate.now()));
        req.setTimeAccepted(Time.valueOf(LocalTime.now()));
        registrationRequestService.acceptRequest(req);
        return "redirect:/admin";
    }

    @GetMapping("/admin/reject/{driverId}")
    public String invalidPage2(Model model) {
        if(!isLoggedIn() || !isAuthorized(model,ROLE_ADMIN))
            return FORBIDDEN_ERROR_PAGE;
        return "redirect:/";
    }

    @PostMapping("/admin/reject/{driverId}")
    public String rejectRequest(@PathVariable("driverId") Long driverId, Model model){
        if(!isLoggedIn() || !isAuthorized(model,ROLE_ADMIN))
            return FORBIDDEN_ERROR_PAGE;
        registrationRequestService.rejectRequest(driverId);
        return "redirect:/admin";
    }

    @GetMapping("/admin/newCoupon")
    public String newCoupon(Model model,RedirectAttributes redirectAttributes) {
        return "newCoupon";
    }

    @PostMapping({"/admin/grantCoupon"})
    public String grantCoupon(@RequestParam(name = "maxDiscount") Integer maxDiscount, @RequestParam(name =
            "couponValidity") Date couponValidity, @RequestParam(name = "sinceDate") Date sinceDate,
                              @RequestParam(name = "numCoupons") Integer numCoupons, Model model,
                              RedirectAttributes redirectAttributes) {
        Date today = Date.valueOf(LocalDate.now());
        if(today.compareTo(couponValidity) > 0 ){
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Coupon Validity Cannot be in past ! !");
            return "redirect:/admin/newCoupon";
        }
        if(sinceDate.compareTo(today) > 0 ){
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Since Date Cannot be In future ! !");
            return "redirect:/admin/newCoupon";
        }


        List<User> couponBeneficiaries = userService.getCouponBeneficiaries(sinceDate, numCoupons);
        numCoupons = numCoupons > couponBeneficiaries.size() ? couponBeneficiaries.size() : numCoupons;
        for (int i = 0; i < numCoupons; i++) {
            String username = couponBeneficiaries.get(i).getUsername();
            Long passengerId = passengerService.getLoggedPassengerIdByUsername(username);
            Coupon coupon = new Coupon();
            coupon.setMaxDiscount(maxDiscount);
            coupon.setCouponValidity(couponValidity);
            coupon.setPassengerId(passengerId);
            couponService.saveCoupon(coupon);
        }
        return "newCoupon";
    }

        @GetMapping("/admin/disputes")
        public String showDisputes(Model model){
            if(!isLoggedIn() || !isAuthorized(model,ROLE_ADMIN))
                return FORBIDDEN_ERROR_PAGE;
            List <TransactionDispute> transactionDisputes = transactionDisputeService.getDisputes();
            model.addAttribute("disputesList",transactionDisputes);
            return "dispute";
        }

        @PostMapping("/admin/disputes/reject/{transactionId}")
        public String rejectDispute(@PathVariable("transactionId") Long transactionId,
                Model model) throws Exception {
            if(!isLoggedIn() || !isAuthorized(model,ROLE_ADMIN))
                return FORBIDDEN_ERROR_PAGE;
            transactionDisputeService.changeDisputeStatus(transactionId,1);
            transactionService.changeTransactionStatus(transactionId,3);
            return "redirect:/admin/disputes";
        }

        @PostMapping("/admin/disputes/accept/{transactionId}")
        public String acceptDispute(@PathVariable("transactionId") Long transactionId, Model model) throws Exception {
            if(!isLoggedIn() || !isAuthorized(model,ROLE_ADMIN))
                return FORBIDDEN_ERROR_PAGE;
            transactionDisputeService.changeDisputeStatus(transactionId,2);
            transactionService.changeTransactionStatus(transactionId,4);
            Long tripId = transactionService.getTransaction(transactionId).getTripId();
            tripService.changeTripStatus(tripId,2);
            return "redirect:/admin/disputes";
        }
        @PostMapping("/admin/disputes/raise/{transactionId}")
        public String raiseDispute(@PathVariable("transactionId") Long transactionId,
                @ModelAttribute("dispute") TransactionDispute transactionDispute,
                Model model) throws Exception {
            if(!isLoggedIn())
                return FORBIDDEN_ERROR_PAGE;
            transactionDispute.setTransactionId(transactionId);
            transactionService.changeTransactionStatus(transactionId,2);
            transactionDisputeService.saveDispute(transactionDispute);
            return "redirect:/passenger/transaction";
        }
    }