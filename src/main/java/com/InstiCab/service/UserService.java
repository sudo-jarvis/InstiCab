package com.InstiCab.service;

import com.InstiCab.models.User;

public interface UserService {
//    void saveUser(User user);
//    void deleteUser(String username);
//    List<String> getUserRoles(String username);
    String findLoggedInUsername();
    User getUserByUsername(String username);
    void saveUser(User user);
}
