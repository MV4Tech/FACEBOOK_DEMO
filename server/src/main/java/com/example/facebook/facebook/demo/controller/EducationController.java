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
    public ResponseEntity<Void> addEducation(@RequestBody @Valid Education education, Authentication authentication){
        educationService.addEducation(education, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get-educations")
    public ResponseEntity<List<EducationDto>> getEducationsByUserId(Authentication authentication){
        return ResponseEntity.ok(educationService.getEducationsByUserId(authentication));
    }

    @DeleteMapping("/delete-education/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id){
        educationService.deleteEducation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-education/{id}")
    public ResponseEntity<Void> updateEducation(@RequestBody @Valid Education education,@PathVariable Long id){
        educationService.updateEducation(education,id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
