package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.UserProfileDto;
import com.example.facebook.facebook.demo.exception.UniqueConstraintException;
import com.example.facebook.facebook.demo.exception.UserNotFoundException;
import com.example.facebook.facebook.demo.model.Address;
import com.example.facebook.facebook.demo.model.Company;
import com.example.facebook.facebook.demo.model.Education;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.UserRepository;
import com.example.facebook.facebook.demo.service.AddressService;
import com.example.facebook.facebook.demo.service.CompanyService;
import com.example.facebook.facebook.demo.service.EducationService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


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
    public UserProfileDto setUserProfileInfo(UserProfileDto userProfileDto, Long id) {
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
        user.setFirstName(userProfileDto.getFirstName());
        user.setLastName(userProfileDto.getLastName());
        user.setGender(userProfileDto.getGender());
        user.setPhoneNumber(userProfileDto.getPhoneNumber());
        user.setDateOfBirth(userProfileDto.getDateOfBirth());

        userRepository.save(user);
        return userProfileDto;

    }



    @Override
    public String setProfileImage(MultipartFile file, long id) throws IOException {
        byte[] bytes = file.getBytes();
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }
        User user = optionalUser.get();
        user.setProfilePicture(bytes);
        userRepository.save(user);
        return "Profile picture uploaded successfully!";
    }

    @Override
    public byte[] displayProfileImage(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }
        User user = optionalUser.get();
        byte[] image = user.getProfilePicture();
        return image;
    }

    @Override
    public String setCoverImage(MultipartFile file, long id) throws IOException {
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
    public byte[] displayCoverImage(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }
        User user = optionalUser.get();
        byte[] image = user.getCoverPhoto();
        return image;
    }

}
