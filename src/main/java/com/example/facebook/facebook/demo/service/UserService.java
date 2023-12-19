package com.example.facebook.facebook.demo.service;


import com.example.facebook.facebook.demo.dto.UserProfileDto;
import com.example.facebook.facebook.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findByEmail(String email);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUserById(Long id);


    UserProfileDto setUserProfileInfo(UserProfileDto userProfileDto, Long id);
}
