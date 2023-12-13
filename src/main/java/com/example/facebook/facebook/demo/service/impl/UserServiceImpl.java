package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.UserRepository;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }
}
