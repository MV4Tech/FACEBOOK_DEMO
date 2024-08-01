package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.EducationDto;
import com.example.facebook.facebook.demo.model.Education;
import com.example.facebook.facebook.demo.service.EducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/education")
public class EducationController {

    private final EducationService educationService;

    // set User education controller
    @PostMapping("/add-education")
    public ResponseEntity<EducationDto> addEducation(@RequestBody @Valid Education education, Authentication authentication){
        EducationDto educationDto = educationService.addEducation(education, authentication);
        return ResponseEntity.ok(educationDto);
    }

    @GetMapping("/get-educations")
    public ResponseEntity<List<EducationDto>> getEducationsByUserId(Authentication authentication){
        return ResponseEntity.ok(educationService.getEducationsByUserId(authentication));
    }

    @DeleteMapping("/delete-education/{id}")
    public ResponseEntity<EducationDto> deleteEducation(@PathVariable Long id, Authentication authentication){
       EducationDto educationDto = educationService.deleteEducation(id, authentication);
        return ResponseEntity.ok(educationDto);
    }

    @PutMapping("/update-education/{id}")
    public ResponseEntity<EducationDto> updateEducation(@RequestBody @Valid Education education,@PathVariable Long id, Authentication authentication){
        EducationDto educationDto = educationService.updateEducation(education,id, authentication);
        return ResponseEntity.ok(educationDto);
    }

}
