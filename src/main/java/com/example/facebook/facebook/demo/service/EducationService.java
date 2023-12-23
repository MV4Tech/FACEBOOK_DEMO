package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.EducationDto;
import com.example.facebook.facebook.demo.model.Education;

import java.util.List;

public interface EducationService {
    public void saveEducation(Education education);

    void addEducation(Education education, Long id);

    List<EducationDto> getEducationsByUserId(Long id);

    void deleteEducation(Long id);

    void updateEducation(Education education, Long id);
}
