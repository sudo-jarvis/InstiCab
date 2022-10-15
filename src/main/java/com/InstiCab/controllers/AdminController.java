package com.InstiCab.controllers;

import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.service.RegistrationRequestService;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController extends BaseController{
    private final UserService userService;
    private final RegistrationRequestService registrationRequestService;
    @Autowired
    public AdminController(UserService userService, RegistrationRequestService registrationRequestService) {
        super(userService);
        this.userService = userService;
        this.registrationRequestService = registrationRequestService;
    }

    @GetMapping({"/admin/", "/admin"})
    public String adminHome(Model model) {
        System.out.println("hi");
        if(!isLoggedIn()) {
            if(isAuthorized(model,ROLE_ADMIN))
                model.addAttribute("isAdmin", true);
            return "redirect:/login/";
        }
        if(!isAuthorized(model,ROLE_ADMIN))
            return FORBIDDEN_ERROR_PAGE;
        List<RegistrationRequest>requestList = registrationRequestService.getPendingRequest();
        model.addAttribute("requestList",requestList);
        model.addAttribute("isAdmin",true);
        System.out.println(requestList.size());
        return "admin";
    }
}
