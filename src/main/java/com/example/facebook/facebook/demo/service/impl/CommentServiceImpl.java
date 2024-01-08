package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.Comment;
import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.CommentRepository;
import com.example.facebook.facebook.demo.service.CommentService;
import com.example.facebook.facebook.demo.service.PostService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

   private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
   private final PostService postService;
   private final UserService userService;
   private final CommentRepository commentRepository;

    @Override
    public void addComment(Comment comment, Long postId, Long userId) {
            Post post = postService.getPostById(postId).get();
            User user = userService.getUserById(userId);
            comment.setPost(post);
            comment.setSenderId(user.getId());
            comment.setUsername(user.getFirstName());
            comment.setDateOfMessaging(LocalDateTime.now());
            commentRepository.save(comment);
            logger.info("Comment added successfully to post - " + post.getId() + "by user - " + user.getUsername());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        logger.info("Comment deleted successfully with id - " + commentId);
    }

}
