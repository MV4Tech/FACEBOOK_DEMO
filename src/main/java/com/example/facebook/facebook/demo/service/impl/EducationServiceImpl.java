package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.exception.UserNotFoundException;
import com.example.facebook.facebook.demo.model.Education;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.CompanyRepository;
import com.example.facebook.facebook.demo.repository.EducationRepository;
import com.example.facebook.facebook.demo.service.EducationService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService{

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
    private final UserService userService;
    private final EducationRepository educationRepository;

    @Override
    public void saveEducation(Education education) {
        educationRepository.save(education);
    }

    @Override
    public Education addEducation(Education education, Long id) {
        Education newEducation = new Education();
        Optional<User> optionalUser = userService.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("No user found with id: "+id+"!");
        }
        newEducation.setName(education.getName());
        newEducation.setTypeOfSchool(education.getTypeOfSchool());
        newEducation.setStartedDate(education.getStartedDate());
        newEducation.setGraduationDate(education.getGraduationDate());
        newEducation.setUser(optionalUser.get());
        educationRepository.save(newEducation);
        logger.info("Education added successfully to user - " + optionalUser.get().getEmail());
        return newEducation;
    }
}
