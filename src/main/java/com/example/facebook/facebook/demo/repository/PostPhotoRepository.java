package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.PostPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostPhotoRepository extends JpaRepository<PostPhoto,Long> {

    List<PostPhoto> findAllByPostId(Long postId);
}
