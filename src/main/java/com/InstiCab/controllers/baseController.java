package com.InstiCab.controllers;

import java.util.Arrays;
import java.util.List;

import com.InstiCab.models.User;
import com.InstiCab.service.UserService;
import org.springframework.ui.Model;

public class baseController {
    protected final UserService userService;
    protected User user;

    // Page Constants
    protected static final String FORBIDDEN_ERROR_PAGE = "error/403";
    protected static final String PAGE_NOT_FOUND_ERROR_PAGE = "error/404";
    protected static final String UNAUTHORIZED_ERROR_PAGE = "error/401";

    // Allowed Role Constants
    protected static final String ROLE_DRIVER = "ROLE_DRIVER";
    protected static final String ROLE_USER = "ROLE_USER";
    protected static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    public baseController(UserService userService) {
        this.userService = userService;
    }

    public boolean isLoggedIn() {
        return userService.findLoggedInUsername() != null;
    }

    public boolean isAuthorized(Model model, String permittedRoles) {
        String username = userService.findLoggedInUsername();
        if (username != null) {
            user = userService.getUserByUsername(username);
            model.addAttribute("username", username);
            model.addAttribute("role", user.getRole());
            model.addAttribute("loggedIn", true);

            List<String> permittedRolesList = Arrays.asList(permittedRoles.split(" "));
            List<String> userRolesList = Arrays.asList(user.getRole().split(" "));
            for (String string : userRolesList) {
                if (permittedRolesList.contains(string)) {
                    return true;
                }
            }
            return false;
        }

        return false;
    }

}
