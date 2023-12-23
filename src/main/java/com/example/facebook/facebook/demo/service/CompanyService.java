package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.CompanyDto;
import com.example.facebook.facebook.demo.model.Company;

import java.util.List;

public interface CompanyService {
    public void save(Company company);

    void addCompany(Company company, Long id);

    List<CompanyDto> getCompaniesByUserId(Long id);

    void deleteCompany(Long id);

    void updateCompany(Company company, Long id);
}
