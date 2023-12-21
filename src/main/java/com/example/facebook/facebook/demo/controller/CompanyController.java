package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Company;
import com.example.facebook.facebook.demo.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    // set User company controller
    @PostMapping("/add-company/{id}")
    public ResponseEntity<Company> addCompany(@RequestBody @Valid Company company, @PathVariable Long id){
        return ResponseEntity.ok(companyService.addCompany(company,id));
    }

}
