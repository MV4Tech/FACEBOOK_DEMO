package com.example.facebook.facebook.demo.service;


import com.example.facebook.facebook.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findByEmail(String email);

    List<User> getAllUsers();
}
