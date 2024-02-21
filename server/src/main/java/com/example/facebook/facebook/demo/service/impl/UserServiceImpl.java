package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.UserProfileDto;
import com.example.facebook.facebook.demo.exception.UniqueConstraintException;
import com.example.facebook.facebook.demo.exception.UserNotFoundException;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.UserRepository;
import com.example.facebook.facebook.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final String FOLDER_PATH = "D:\\facebook\\images";

    @Override
    public Long findUserIdByAuthentication(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return user.getId();
    }


    // save user w
    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }


    // find user by email
    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()){
            throw new UserNotFoundException("No user found with email: "+email+"!");
        }
       return user;
    }

    @Override
    public Optional<User> FindByEmailNotOptional(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }
        return user;
    }


    // get all users
    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new UserNotFoundException("No users found!");
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }
        return user.get();
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserProfileDto setUserProfileInfo(UserProfileDto userProfileDto, Authentication authentication) {
        Long id = findUserIdByAuthentication(authentication);
        
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }
        List<User> users = userRepository.findAll();
        for(User user : users){
            if(userProfileDto.getPhoneNumber().equals(user.getPhoneNumber())){
                throw new UniqueConstraintException("Phone number already exists!");
            }
        }
        User user = optionalUser.get();
        user.setGender(userProfileDto.getGender());
        user.setPhoneNumber(userProfileDto.getPhoneNumber());
        user.setDateOfBirth(userProfileDto.getDateOfBirth());

        userRepository.save(user);
        return userProfileDto;

    }



    @Override
    public String setProfileImage(MultipartFile file, Authentication authentication) throws IOException {

        String filePaths = FOLDER_PATH + "\\" + file.getOriginalFilename();
        file.transferTo(new File(filePaths));
        Long id = findUserIdByAuthentication(authentication);
        Optional<User> optionalUser = userRepository.findById(id);

        User user = optionalUser.get();
        user.setProfilePicturePath(filePaths);
        userRepository.save(user);
        return "Profile picture uploaded successfully!";
    }

    @Override
    public byte[] displayProfileImage(Authentication authentication) throws IOException {
        Long id = findUserIdByAuthentication(authentication);
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        String filePath = user.getProfilePicturePath();

        byte[] image = Files.readAllBytes(new File(filePath).toPath());
        return image;
    }
/*
    @Override
    public String setCoverImage(MultipartFile file,Authentication authentication) throws IOException {
        Long id = findUserIdByAuthentication(authentication);
        byte[] bytes = file.getBytes();
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }
        User user = optionalUser.get();
        user.setCoverPhoto(bytes);
        userRepository.save(user);
        return "Cover picture uploaded successfully!";
    }

    @Override
    public byte[] displayCoverImage(Authentication authentication) {
        Long id = findUserIdByAuthentication(authentication);
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }
        User user = optionalUser.get();
        byte[] image = user.getCoverPhoto();
        return image;
    }

 */

}
