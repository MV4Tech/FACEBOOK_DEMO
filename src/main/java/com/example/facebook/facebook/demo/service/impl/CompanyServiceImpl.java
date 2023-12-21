package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.exception.UserNotFoundException;
import com.example.facebook.facebook.demo.model.Company;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.CompanyRepository;
import com.example.facebook.facebook.demo.service.CompanyService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
    private final CompanyRepository companyRepository;
    private final UserService userService;

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company addCompany(Company company, Long id) {
        Company newCompany = new Company();
        Optional<User> optionalUser = userService.findById(id);

        newCompany.setName(company.getName());
        newCompany.setStartedDate(company.getStartedDate());
        newCompany.setEndDate(company.getEndDate());
        newCompany.setUser(optionalUser.get());
        companyRepository.save(newCompany);
        logger.info("Company added successfully to user - " + optionalUser.get().getEmail());
        return newCompany;
    }
}
