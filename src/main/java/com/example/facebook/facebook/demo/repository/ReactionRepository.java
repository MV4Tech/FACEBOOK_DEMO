package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Long> {

    List<Reaction> findAllByPostId(Long postId);
}
