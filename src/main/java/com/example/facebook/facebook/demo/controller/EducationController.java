package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Education;
import com.example.facebook.facebook.demo.service.EducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/education")
public class EducationController {

    private final EducationService educationService;

    // set User education controller
    @PostMapping("/add-education/{id}")
    public ResponseEntity<Education> addEducation(@RequestBody @Valid Education education, @PathVariable Long id){
        return ResponseEntity.ok(educationService.addEducation(education,id));
    }


}
