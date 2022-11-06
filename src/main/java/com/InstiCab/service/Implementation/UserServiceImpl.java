package com.InstiCab.service.Implementation;

import com.InstiCab.dao.PassengerDAO;
import com.InstiCab.dao.UserDAO;
import com.InstiCab.models.User;
import com.InstiCab.service.FavouriteLocationService;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserDAO userDAO;
    private final FavouriteLocationService favouriteLocationService;
    private final PassengerDAO passengerDAO;
    @Autowired
    public UserServiceImpl(UserDAO userDAO, FavouriteLocationService favouriteLocationService,
                           PassengerDAO passengerDAO) {
        this.userDAO = userDAO;
        this.favouriteLocationService = favouriteLocationService;
        this.passengerDAO = passengerDAO;
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
        userDAO.createUser(user);
    }

    private Collection<SimpleGrantedAuthority> makeAuthorities(String userRoles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String[] roles = userRoles.split(" ");
        for (String role : roles)
            authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public List<String> getUserRoles(String username) {
        String roles = getUserByUsername(username).getRole();
        String[] rolesArray = roles.split(" ");
        return new ArrayList<>(Arrays.asList(rolesArray));
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserDataById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserDataByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                makeAuthorities(user.getRole()));
    }

    @Override
    public void updateLoginDetails(String username) throws Exception {
        userDAO.updateLastLogin(username);
    }

    @Override
    public List<User> getCouponBeneficiaries(Date sinceDate, Integer numCoupons) {
        return userDAO.getCouponBeneficiaries(sinceDate, numCoupons);
    }
}
