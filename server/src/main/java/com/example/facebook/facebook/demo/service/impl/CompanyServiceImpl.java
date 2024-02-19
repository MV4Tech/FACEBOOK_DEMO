package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.CompanyDto;
import com.example.facebook.facebook.demo.exception.CompanyNotFoundException;
import com.example.facebook.facebook.demo.exception.UserNotFoundException;
import com.example.facebook.facebook.demo.model.Company;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.CompanyRepository;
import com.example.facebook.facebook.demo.service.CompanyService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void addCompany(Company company, Authentication authentication) {
        Company newCompany = new Company();

        Long id = userService.findUserIdByAuthentication(authentication);

        Optional<User> optionalUser = userService.findById(id);

        newCompany.setName(company.getName());
        newCompany.setStartedDate(company.getStartedDate());
        newCompany.setEndDate(company.getEndDate());
        newCompany.setUser(optionalUser.get());
        companyRepository.save(newCompany);
        logger.info("Company added successfully to user - " + optionalUser.get().getEmail());
    }

    @Override
    public List<CompanyDto> getCompaniesByUserId(Authentication authentication) {
        Long id = userService.findUserIdByAuthentication(authentication);
        List<Company> companies = companyRepository.findAllByUserId(id);
        if(!(companies.size() > 0)){
            throw new CompanyNotFoundException("Company not found");
        }

        List<CompanyDto> companyDtos = companies.stream().map(company ->{
            CompanyDto companyDto = new CompanyDto();
            companyDto.setId(company.getId());
            companyDto.setName(company.getName());
            companyDto.setStartedDate(company.getStartedDate());
            companyDto.setEndDate(company.getEndDate());
            return companyDto;
        }).collect(Collectors.toList());
        logger.info("Companies fetched successfully for user");
        return companyDtos;
    }

    @Override
    public void deleteCompany(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(!optionalCompany.isPresent()){
            throw new CompanyNotFoundException("Company not found");
        }
        companyRepository.deleteById(id);
        logger.info("Company deleted successfully");
    }

    @Override
    public void updateCompany(Company company, Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(!optionalCompany.isPresent()){
            throw new CompanyNotFoundException("Company not found");
        }
        Company updatedCompany = optionalCompany.get();
        updatedCompany.setName(company.getName());
        updatedCompany.setStartedDate(company.getStartedDate());
        updatedCompany.setEndDate(company.getEndDate());
        companyRepository.save(updatedCompany);
        logger.info("Company updated successfully");
    }
}
