package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.dto.ReactionDto;
import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Long> {

    List<Reaction> findAllByPostId(Long postId);

    Optional<Reaction> findByPost(Post post);
}
