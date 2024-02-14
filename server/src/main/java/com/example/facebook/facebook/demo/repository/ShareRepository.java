package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
    @Query("SELECT COUNT(s) FROM Share s WHERE s.post.id = ?1")
    Integer countSharesByPostId(Long postId);
}
