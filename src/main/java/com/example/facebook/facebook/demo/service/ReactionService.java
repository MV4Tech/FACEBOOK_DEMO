package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.Reaction;

import java.util.List;

public interface ReactionService {
    void addReactionToPost(Reaction reaction, Long postId, Long userId);

    List<Reaction> getReactionsByPostId(Long postId);
}
