package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

    List<Post> findAllByUserId(Long userId);

    Set<Post> findAllByPageId(Long pageId);
}
