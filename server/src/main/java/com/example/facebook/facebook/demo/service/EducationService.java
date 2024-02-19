package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.EducationDto;
import com.example.facebook.facebook.demo.model.Education;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface EducationService {
    public void saveEducation(Education education);

    void addEducation(Education education, Authentication authentication);

    List<EducationDto> getEducationsByUserId(Authentication authentication);

    void deleteEducation(Long id);

    void updateEducation(Education education, Long id);
}
