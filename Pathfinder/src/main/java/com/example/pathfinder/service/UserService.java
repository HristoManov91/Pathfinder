package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.UserEntity;
import com.example.pathfinder.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel usm);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logout();

    UserServiceModel findUserById(Long id);

    boolean isUsernameExists(String username);
}
