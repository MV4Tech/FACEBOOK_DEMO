package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long>{

    Optional<Page> findByOwnerId(Long id);
}
