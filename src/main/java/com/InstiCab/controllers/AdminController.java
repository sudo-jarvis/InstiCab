package com.InstiCab.controllers;

import com.InstiCab.models.Coupon;
import com.InstiCab.models.Driver;
import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.models.User;
import com.InstiCab.service.CouponService;
import com.InstiCab.service.DriverService;
import com.InstiCab.service.RegistrationRequestService;
import com.InstiCab.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController extends BaseController{
    private CouponService couponService;
    @Getter
    @Setter
    static class RequestDetails {
        private Driver driver;
        private RegistrationRequest registrationRequest;
    }

    @Autowired
    public AdminController(UserService userService,
                           DriverService driverService, RegistrationRequestService registrationRequestService, CouponService couponService) {
        super(userService,driverService,registrationRequestService);
        this.couponService = couponService;
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

    @GetMapping("/admi![](../../../../resources/static/images/nyc-taxis-gty-rc-200220_hpMain_16x9_992.jpg)n/accept/{driverId}")
    public String invalidPage(Model model){
        if(!isLoggedIn() || !isAuthorized(model,ROLE_ADMIN))
            return FORBIDDEN_ERROR_PAGE;
        return "redirect:/";
    }

    @PostMapping("/admin/accept/{driverId}")
    public String acceptRequest(@PathVariable("driverId") Long driverId, Model model){

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
        registrationRequestService.rejectRequest(driverId);
        return "redirect:/admin";
    }

//    @PostMapping({"/admin/grantCoupon"})
//    public String grantCoupon(@RequestParam(name = "maxDiscount") Integer maxDiscount, @RequestParam(name = "couponValidity") Date couponValidity, Model model) {
//        for(int i=0; i<5; i++){
//            Coupon coupon = new Coupon();
//            coupon.setMaxDiscount(maxDiscount);
//            coupon.setCouponValidity(couponValidity);
//            couponService.saveCoupon(coupon);
//        }
//        return "newCoupon";
//    }
}
