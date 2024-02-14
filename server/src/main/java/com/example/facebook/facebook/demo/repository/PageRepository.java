package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long>{
    
}
