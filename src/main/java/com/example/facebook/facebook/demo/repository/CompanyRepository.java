package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
    List<Company> findAllByUserId(Long id);
}
