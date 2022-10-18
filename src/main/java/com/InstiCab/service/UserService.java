package com.InstiCab.service;

import com.InstiCab.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    String findLoggedInUsername();
    User getUserByUsername(String username);
    void saveUser(User user);
    List<String> getUserRoles(String username);
    User getUserById(int id);
    List<User> getAllUsers();
    UserDetails loadUserByUsername(String username);

    void updateLoginDetails(String username) throws Exception;
}
