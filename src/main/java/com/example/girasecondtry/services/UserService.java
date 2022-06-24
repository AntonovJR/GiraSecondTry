package com.example.girasecondtry.services;


import com.example.girasecondtry.models.entities.User;
import com.example.girasecondtry.models.service.UserServiceModel;

import java.util.Optional;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    User findUser(String email);

    UserServiceModel findByEmailAndPassword(String email, String password);

    void loginUser(UserServiceModel userServiceModel);

    void logout();

    Optional<User> findUserById(Long id);
}
