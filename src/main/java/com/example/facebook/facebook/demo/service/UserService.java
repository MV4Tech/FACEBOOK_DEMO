package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }
}
