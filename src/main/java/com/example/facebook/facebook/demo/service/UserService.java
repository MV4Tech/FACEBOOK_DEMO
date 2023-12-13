package com.example.facebook.facebook.demo.service;


import com.example.facebook.facebook.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    User saveUser(User user);

}
