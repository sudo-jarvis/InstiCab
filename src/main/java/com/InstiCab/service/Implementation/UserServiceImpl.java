package com.InstiCab.service.Implementation;

import com.InstiCab.dao.UserDAO;
import com.InstiCab.models.User;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetails)
            return ((UserDetails) userDetails).getUsername();
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserDataByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setRole("ROLE_PASSENGER");
        userDAO.createUser(user);
    }
}
