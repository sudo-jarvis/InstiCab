package com.InstiCab.controllers;

import com.InstiCab.models.User;
import com.InstiCab.service.UserService;
import lombok.Getter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class loginController extends baseController{
    public loginController(UserService userService) {
        super(userService);
    }

    @GetMapping("/login/")
    public String loginGoto(Model model){
        if(isLoggedIn()){
            return "redirect:/";
        }
        model.addAttribute("user",new User());
        return "login";
    }


}
