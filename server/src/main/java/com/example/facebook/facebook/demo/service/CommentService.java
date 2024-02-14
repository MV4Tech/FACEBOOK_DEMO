package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.CommentDto;
import com.example.facebook.facebook.demo.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    void addComment(Comment comment);

    void deleteComment(Long commentId);

    void editComment(Comment comment, Long id);


    List<CommentDto> getCommentsByPostId(Long postId);

    Optional<Comment> getCommentById(Long id);
}
