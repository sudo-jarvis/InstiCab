package com.InstiCab.controllers;

import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController{
    @Autowired
    public HomeController(UserService userService) {
        super(userService);
    }

    @GetMapping({ "/", "" })
    public String home(Model model) {
        if (isLoggedIn()) {
            System.out.println("Welcome to Home page!");
            if (isAuthorized(model, ROLE_ADMIN))
                return "admin";
            else return "home";
        }
        return "redirect:/login/";
    }



}
