package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
