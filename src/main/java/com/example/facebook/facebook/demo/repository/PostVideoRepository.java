package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.PostVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostVideoRepository extends JpaRepository<PostVideo,Long> {
    List<PostVideo> findAllByPostId(Long postId);
}
