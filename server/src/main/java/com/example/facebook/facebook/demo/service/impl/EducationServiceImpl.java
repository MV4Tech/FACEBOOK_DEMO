package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.EducationDto;
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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void addEducation(Education education, Authentication authentication) {
        Education newEducation = new Education();
        Long id = userService.findUserIdByAuthentication(authentication);
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
    }

    @Override
    public List<EducationDto> getEducationsByUserId(Authentication authentication) {
        Long id = userService.findUserIdByAuthentication(authentication);
        List<Education> educations = educationRepository.findAllByUserId(id);
        if(!(educations.size() > 0)){
            throw new UserNotFoundException("Education not found");
        }
        List<EducationDto> educationDtos = educations.stream().map(education -> {
            EducationDto educationDto = new EducationDto();
            educationDto.setId(education.getId());
            educationDto.setName(education.getName());
            educationDto.setTypeOfSchool(education.getTypeOfSchool());
            educationDto.setStartedDate(education.getStartedDate());
            educationDto.setGraduationDate(education.getGraduationDate());
            return educationDto;
        }).collect(java.util.stream.Collectors.toList());
        logger.info("Education fetched successfully for user");
        return educationDtos;
    }

    @Override
    public void deleteEducation(Long id) {
        Optional<Education> optionalEducation = educationRepository.findById(id);
        if(!optionalEducation.isPresent()){
            throw new UserNotFoundException("Education not found");
        }
        educationRepository.deleteById(id);
        logger.info("Education deleted successfully");
    }

    @Override
    public void updateEducation(Education education, Long id) {
        Optional<Education> optionalEducation = educationRepository.findById(id);
        if(!optionalEducation.isPresent()){
            throw new UserNotFoundException("Education not found");
        }
        Education updatedEducation = optionalEducation.get();
        updatedEducation.setName(education.getName());
        updatedEducation.setTypeOfSchool(education.getTypeOfSchool());
        updatedEducation.setStartedDate(education.getStartedDate());
        updatedEducation.setGraduationDate(education.getGraduationDate());
        educationRepository.save(updatedEducation);
        logger.info("Education updated successfully to user - " + optionalEducation.get().getUser().getEmail());
    }
}
