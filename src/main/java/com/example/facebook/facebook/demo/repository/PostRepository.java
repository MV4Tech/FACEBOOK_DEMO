package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
