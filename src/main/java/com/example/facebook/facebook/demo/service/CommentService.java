package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.Comment;

public interface CommentService {
    void addComment(Comment comment, Long postId, Long userId);
}
