package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.Education;
import com.example.facebook.facebook.demo.repository.EducationRepository;
import com.example.facebook.facebook.demo.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService{
    private final EducationRepository educationRepository;
    @Override
    public void saveEducation(Education education) {
        educationRepository.save(education);
    }
}
