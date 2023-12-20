package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long>{

}
