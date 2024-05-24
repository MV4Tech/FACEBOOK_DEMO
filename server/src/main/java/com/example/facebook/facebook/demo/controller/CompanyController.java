package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.CompanyDto;
import com.example.facebook.facebook.demo.model.Company;
import com.example.facebook.facebook.demo.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    // set User company controller
    @PostMapping("/add-company")
    public ResponseEntity<CompanyDto> addCompany(@RequestBody @Valid Company company, Authentication authentication){
       CompanyDto companyDto = companyService.addCompany(company, authentication);
        return ResponseEntity.ok(companyDto);
    }

    // get all companies from user controller
    @GetMapping("/get-companies")
    public ResponseEntity<List<CompanyDto>> getCompaniesByUserId(Authentication authentication){
        return ResponseEntity.ok(companyService.getCompaniesByUserId(authentication));
    }

    @DeleteMapping("/delete-company/{id}")
    public ResponseEntity<CompanyDto> deleteCompany(@PathVariable Long id, Authentication authentication){
        CompanyDto companyDto = companyService.deleteCompany(id, authentication);
        return ResponseEntity.ok(companyDto);
    }

    @PutMapping("/update-company/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody @Valid Company company,@PathVariable Long id, Authentication authentication){
       CompanyDto companyDto = companyService.updateCompany(company,id,authentication);
        return ResponseEntity.ok(companyDto);
    }


}
