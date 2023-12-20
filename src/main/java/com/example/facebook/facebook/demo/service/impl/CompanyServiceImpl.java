package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.Company;
import com.example.facebook.facebook.demo.repository.CompanyRepository;
import com.example.facebook.facebook.demo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }
}
