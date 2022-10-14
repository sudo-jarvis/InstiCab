package com.InstiCab.controllers;

import com.InstiCab.models.User;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class BaseController {
    protected final UserService userService;
    protected User user;

    // Page Constants
    protected static final String FORBIDDEN_ERROR_PAGE = "error/403";
    protected static final String PAGE_NOT_FOUND_ERROR_PAGE = "error/404";
    protected static final String UNAUTHORIZED_ERROR_PAGE = "error/401";

    // Allowed Role Constants
    protected static final String ROLE_DRIVER = "ROLE_DRIVER";
    protected static final String ROLE_PASSENGER = "ROLE_PASSENGER";
    protected static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    public BaseController(UserService userService) {
        this.userService = userService;
    }

    public boolean isLoggedIn() {
        return userService.findLoggedInUsername() != null;
    }

    public boolean isAuthorized(Model model, String checkRole) {
        String username = userService.findLoggedInUsername();
        if (username != null) {
            user = userService.getUserByUsername(username);
            return user.getRole().equals(checkRole);
        }

        return false;
    }

}
