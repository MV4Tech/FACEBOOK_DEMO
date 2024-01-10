package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.ReactionDto;
import com.example.facebook.facebook.demo.model.Reaction;

import java.util.List;

public interface ReactionService {
    void addReactionToPost(Reaction reaction);

    List<ReactionDto> getReactionsByPostId(Long postId);

    void deleteReactionFromPost(Long reactionId);

    Reaction updateReaction(Reaction reaction, Long id);
}
