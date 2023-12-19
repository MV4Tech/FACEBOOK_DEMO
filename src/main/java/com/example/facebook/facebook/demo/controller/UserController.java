package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.UserProfileDto;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // save user controller
    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    // get all users controller
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/profile-info/{id}")
    public ResponseEntity<UserProfileDto> setUserProfileInfo(@RequestBody UserProfileDto userProfileDto, @PathVariable Long id){
        return ResponseEntity.ok(userService.setUserProfileInfo(userProfileDto,id));
    }

    // delete user by id controller
    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }


}
