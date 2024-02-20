package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.CommentDto;
import com.example.facebook.facebook.demo.exception.CommentNotFoundException;
import com.example.facebook.facebook.demo.model.Comment;
import com.example.facebook.facebook.demo.model.Notification;
import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.CommentRepository;
import com.example.facebook.facebook.demo.service.CommentService;
import com.example.facebook.facebook.demo.service.NotificationService;
import com.example.facebook.facebook.demo.service.PostService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

   private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
   private final PostService postService;
   private final UserService userService;
   private final CommentRepository commentRepository;
   private final NotificationService notificationService;

    @Override
    public void addComment(Comment comment, Authentication authentication) {
            long userId = userService.findUserIdByAuthentication(authentication);
            Post post = postService.getPostById(comment.getPost().getId()).get();
            User user = userService.findById(userId).get();
            comment.setPost(post);
            comment.setSenderId(user);
            comment.setUsername(user.getFirstName());
            comment.setDateOfMessaging(LocalDateTime.now());
            comment.setIsEdited(false);
            commentRepository.save(comment);

        Notification notification = Notification.builder()
                .sender(user)
                .receiver(post.getUser())
                .message(user.getFirstName() + " " + user.getLastName() + " commented on your post " + post.getPostHead())
                .sentTime(comment.getDateOfMessaging())
                .build();
        // TODO: kogato e edin i susht user da ne se izprati notifikaciq
            notificationService.sendNotification(notification,authentication);
            logger.info("Comment added successfully to post - " + post.getId() + "by user - " + user.getUsername());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        logger.info("Comment deleted successfully with id - " + commentId);
    }

    @Override
    public void editComment(Comment comment, Long id) {
        Optional<Comment> OptionalCommentToEdit = commentRepository.findById(id);

        if(!OptionalCommentToEdit.isPresent()){
            throw new RuntimeException("Comment with id: "+id+" not found!");
        }
        Comment commentToEdit = OptionalCommentToEdit.get();
        commentToEdit.setComment(comment.getComment());
        commentToEdit.setDateOfMessaging(LocalDateTime.now());
        commentToEdit.setIsEdited(true);
        commentRepository.save(commentToEdit);
        logger.info("Comment edited successfully with id - " + id);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> commentsByPostId = commentRepository.findAllByPostId(postId);
        if(commentsByPostId.isEmpty()){
            throw new CommentNotFoundException("No comments found for post with id: "+postId+"!");
        }
        List<CommentDto> commentDtoList = commentsByPostId.stream().map(comment ->
            new CommentDto(
                    comment.getId(),
                    comment.getComment(),
                    comment.getDateOfMessaging(),
                    comment.getSenderId().getId(),
                    comment.getUsername(),
                    comment.getIsEdited()
            )).collect(Collectors.toList());
        logger.info("Comments retrieved successfully from post - " + postId);
        return commentDtoList;
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

}
