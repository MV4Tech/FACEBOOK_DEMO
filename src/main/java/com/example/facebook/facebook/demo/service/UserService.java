package com.example.facebook.facebook.demo.service;


import com.example.facebook.facebook.demo.dto.UserProfileDto;
import com.example.facebook.facebook.demo.model.Address;
import com.example.facebook.facebook.demo.model.Company;
import com.example.facebook.facebook.demo.model.Education;
import com.example.facebook.facebook.demo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUserById(Long id);

    UserProfileDto setUserProfileInfo(UserProfileDto userProfileDto, Long id);


    String setProfileImage(MultipartFile file, long id) throws IOException;

    byte[] displayProfileImage(long id);

    String setCoverImage(MultipartFile file, long id) throws IOException;

    byte[] displayCoverImage(long id);
}
