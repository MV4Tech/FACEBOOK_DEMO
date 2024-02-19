package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.NotificationDto;
import com.example.facebook.facebook.demo.dto.UserProfileDto;
import com.example.facebook.facebook.demo.model.Address;
import com.example.facebook.facebook.demo.model.Company;
import com.example.facebook.facebook.demo.model.Education;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // save user controller
    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    // get all users controller
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // set User profile info controller
    @PostMapping("/profile-info")
    public ResponseEntity<UserProfileDto> setUserProfileInfo(@RequestBody @Valid UserProfileDto userProfileDto, Authentication authentication){
        return ResponseEntity.ok(userService.setUserProfileInfo(userProfileDto,authentication));
    }

    // add profile picture controller
    @PostMapping("/add-profile-picture/{id}")
    public ResponseEntity<?> addProfileImage(@RequestParam("image") MultipartFile file,@PathVariable Long id) throws IOException {
        String uploadMessage = userService.setProfileImage(file,id);

        return ResponseEntity.status(HttpStatus.OK).body(uploadMessage);
    }

    // display profile picture controller
    @GetMapping("/display-profile-picture/{id}")
    public ResponseEntity<byte[]> displayProfileImage(@PathVariable("id") long id) {
        byte[] imageBytes = userService.displayProfileImage(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    // add cover picture controller
    @PostMapping("/add-cover-picture/{id}")
    public ResponseEntity<?> addCoverImage(@RequestParam("image") MultipartFile file,@PathVariable long id) throws IOException {
        String uploadMessage = userService.setCoverImage(file,id);

        return ResponseEntity.status(HttpStatus.OK).body(uploadMessage);
    }

    // display cover picture controller
    @GetMapping("/display-cover-picture/{id}")
    public ResponseEntity<byte[]> displayCover(@PathVariable("id") long id) {
        byte[] imageBytes = userService.displayCoverImage(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    // delete user by id controller
    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }





}
