package com.InstiCab.controllers;

import com.InstiCab.models.Trip;
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

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
    public String CreateHospitalRequest(Model model, RedirectAttributes redirectAttributes) throws ParseException {
        if(!isLoggedIn() || isAuthorized(model,ROLE_ADMIN)){
            redirectAttributes.addFlashAttribute("errorMsg","Proper Authorization Required !");
            return FORBIDDEN_ERROR_PAGE;
        }
        String time = "00:00:00";
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat2.format(new Date());
        String dateTime = today + " " + time;
        Date d = dateFormat1.parse(dateTime);
        redirectAttributes.addFlashAttribute("successMsg","Emergency Request Succesfully Sent !");
        emergencyService.createHospitalRequest();
        Timer timer = new Timer();
        TimerTask scheduleRequestDelete = new TimerTask(){
            @Override
            public void run(){
                try {
                    emergencyService.deleteRequest();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule(scheduleRequestDelete, d);
        return "redirect:/";
    }

    @PostMapping("/EmergencyRequest/police")
    public String CreatePoliceRequest(Model model,RedirectAttributes redirectAttributes) throws ParseException {
        if(!isLoggedIn() || isAuthorized(model,ROLE_ADMIN)){
            redirectAttributes.addFlashAttribute("errorMsg","Proper Authorization Required !");
            return FORBIDDEN_ERROR_PAGE;
        }
        String time = "00:00:00";
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat2.format(new Date());
        String dateTime = today + " " + time;
        Date d = dateFormat1.parse(dateTime);
        redirectAttributes.addFlashAttribute("successMsg","Emergency Request Succesfully Sent !");
        emergencyService.createPoliceRequest();
        Timer timer = new Timer();
        TimerTask scheduleRequestDelete = new TimerTask(){
            @Override
            public void run(){
                try {
                    emergencyService.deleteRequest();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule(scheduleRequestDelete, d);
        return "redirect:/";
    }

    @PostMapping("/EmergencyRequest/fire")
    public String CreateFireStationRequest(Model model,RedirectAttributes redirectAttributes) throws ParseException {
        if(!isLoggedIn() || isAuthorized(model,ROLE_ADMIN)){
            redirectAttributes.addFlashAttribute("errorMsg","Proper Authorization Required !");
            return FORBIDDEN_ERROR_PAGE;
        }
        String time = "00:00:00";
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat2.format(new Date());
        String dateTime = today + " " + time;
        Date d = dateFormat1.parse(dateTime);
        redirectAttributes.addFlashAttribute("successMsg","Emergency Request Succesfully Sent !");
        emergencyService.createFireStationRequest();
        Timer timer = new Timer();
        TimerTask scheduleRequestDelete = new TimerTask(){
            @Override
            public void run(){
                try {
                    emergencyService.deleteRequest();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule(scheduleRequestDelete, d);
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
