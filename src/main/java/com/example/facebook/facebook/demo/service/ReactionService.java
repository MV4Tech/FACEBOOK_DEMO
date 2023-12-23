package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.Reaction;

public interface ReactionService {
    void addReactionToPost(Reaction reaction, Long postId, Long userId);
}
