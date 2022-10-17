package com.InstiCab.service;

import com.InstiCab.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
//    void saveUser(User user);
//    void deleteUser(String username);
//    List<String> getUserRoles(String username);
    String findLoggedInUsername();
    User getUserByUsername(String username);
    void saveUser(User user);
    List<String> getUserRoles(String username);
    User getUserById(int id);
    List<User> getAllUsers();
    UserDetails loadUserByUsername(String username);

}
