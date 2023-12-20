package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.UserProfileDto;
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
    private final AddressService addressService;
    private final CompanyService companyService;
    private final EducationService educationService;

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
        User user = optionalUser.get();
        user.setFirstName(userProfileDto.getFirstName());
        user.setLastName(userProfileDto.getLastName());
        user.setGender(userProfileDto.getGender());
        user.setDateOfBirth(userProfileDto.getDateOfBirth());
        user.setPhoneNumber(userProfileDto.getPhoneNumber());
        userRepository.save(user);
        return userProfileDto;

    }

    @Override
    public Address addAddress(Address address, Long id) {
        Address newAddress = new Address();
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }

        newAddress.setCity(address.getCity());
        newAddress.setCountry(address.getCountry());
        newAddress.setMunicipality(address.getMunicipality());
        newAddress.setUser(optionalUser.get());
        addressService.save(newAddress);
        return newAddress;
    }

    @Override
    public Company addCompany(Company company, Long id) {
        Company newCompany = new Company();
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }

        newCompany.setName(company.getName());
        newCompany.setStartedDate(company.getStartedDate());
        newCompany.setEndDate(company.getEndDate());
        newCompany.setUser(optionalUser.get());
        companyService.save(newCompany);

        return newCompany;
    }

    @Override
    public Education addEducation(Education education, Long id) {
        Education newEducation = new Education();
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }
        newEducation.setName(education.getName());
        newEducation.setTypeOfSchool(education.getTypeOfSchool());
        newEducation.setStartedDate(education.getStartedDate());
        newEducation.setGraduationDate(education.getGraduationDate());
        newEducation.setUser(optionalUser.get());
        educationService.saveEducation(newEducation);
        return newEducation;
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
