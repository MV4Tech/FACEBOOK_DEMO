package com.example.facebook.facebook.demo.service;


import com.example.facebook.facebook.demo.dto.UserProfileDto;
import com.example.facebook.facebook.demo.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    
    
    User saveUser(User user);

    Optional<User> findByEmail(String email);

    Optional<User> FindByEmailNotOptional(String email);

    Optional<User> findById(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);

    User deleteUserById(Long id);

    UserProfileDto setUserProfileInfo(UserProfileDto userProfileDto,Authentication authentication);


    String setProfileImage(MultipartFile file, Authentication authentication) throws IOException;

    byte[] displayProfileImage(Authentication authentication) throws IOException;

    String setCoverImage(MultipartFile file,Authentication authentication) throws IOException;

    byte[] displayCoverImage(Authentication authentication) throws IOException;



    Long findUserIdByAuthentication(Authentication authentication);

    void enableUser(String email);
}
