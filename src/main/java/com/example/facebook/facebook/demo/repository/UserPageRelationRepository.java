package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.UserPageRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPageRelationRepository extends JpaRepository<UserPageRelation, Long> {
    List<UserPageRelation> findAllByUserId(Long userId);

    List<UserPageRelation> findAllByPageId(Long pageId);

    Long countAllByPageId(Long pageId);

    Long countAllByUserId(Long userId);
}
