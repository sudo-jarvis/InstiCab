package com.InstiCab.controllers;

import com.InstiCab.models.User;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class RegisterController extends BaseController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerGoto(Model model) {
        if (isLoggedIn()) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "register";
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public String handleDuplicateKeyException(Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMsg", "Username not available!");
        return "redirect:/register/";
    }

    @PostMapping("/register/")
    public String registerManager(@ModelAttribute("user") User user, Model model,
                                  RedirectAttributes redirectAttributes) {
        user.setDateCreated(Date.valueOf(LocalDate.now()));
        user.setLastLoginTime(Time.valueOf(LocalTime.now()));
        user.setLastLoginDate(Date.valueOf(LocalDate.now()));
        user.setRole(ROLE_PASSENGER);
        if (isLoggedIn()) {
            return "redirect:/";
        }

        User userToSave = new User(user.getUsername(), user.getFirstName(), user.getMiddleName(),
                user.getLastName(), user.getEmail(), user.getPhoneNo(), user.getPassword(),
                user.getDateCreated(), user.getLastLoginDate(),
                user.getLastLoginTime(), user.getRole());
        userService.saveUser(userToSave);

        redirectAttributes.addFlashAttribute("successMsg",
                "Registered successfully!");
        return "redirect:/";
    }
}
