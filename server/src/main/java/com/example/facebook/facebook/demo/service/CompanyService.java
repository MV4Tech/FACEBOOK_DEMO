package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.CompanyDto;
import com.example.facebook.facebook.demo.model.Company;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CompanyService {
    public void save(Company company);

    void addCompany(Company company, Authentication authentication);

    List<CompanyDto> getCompaniesByUserId(Authentication authentication);

    void deleteCompany(Long id);

    void updateCompany(Company company, Long id);
}
