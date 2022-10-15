package com.InstiCab.service.Implementation;

import com.InstiCab.dao.UserDAO;
import com.InstiCab.models.User;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserDataByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                makeAuthorities(user.getRole()));
    }
}
