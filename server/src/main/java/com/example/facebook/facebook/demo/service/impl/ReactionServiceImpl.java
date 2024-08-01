package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.ReactionDto;
import com.example.facebook.facebook.demo.exception.ReactionNotFoundException;
import com.example.facebook.facebook.demo.model.*;
import com.example.facebook.facebook.demo.repository.ReactionRepository;
import com.example.facebook.facebook.demo.service.*;
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
public class ReactionServiceImpl implements ReactionService {

    private static final Logger logger = LoggerFactory.getLogger(ReactionServiceImpl.class);
    private final ReactionRepository reactionRepository;
    private final PostService postService;
    private final UserService userService;
    private final NotificationService notificationService;
    private final CommentService commentService;

    @Override
    public void addReactionToPost(Reaction reaction, Authentication authentication) {
        long userId = userService.findUserIdByAuthentication(authentication);
        Optional<Post> OptionalPost = postService.getPostById(reaction.getPost().getId());
        Optional<User> OptionalUser = userService.findById(userId);
        reaction.setPost(OptionalPost.get());
        reaction.setUsername(OptionalUser.get().getFirstName());
        reaction.setSenderId(OptionalUser.get());
        reaction.setFlag(true);
        reactionRepository.save(reaction);
        Notification notification = Notification.builder()
                .sender(OptionalUser.get())
                .receiver(OptionalPost.get().getUser())
                .message(OptionalUser.get().getFirstName() + " " + OptionalUser.get().getLastName() + " reacted with "+  reaction.getReact() + " to your post with " + OptionalPost.get().getPostHead())
                .sentTime(LocalDateTime.now())
                .build();
        notificationService.sendNotification(notification,authentication);
        logger.info("Reaction added successfully to post - " + OptionalPost.get().getId());
    }

    @Override
    public List<ReactionDto> getReactionsByPostId(Long postId) {
        Optional<Post> OptionalPost = postService.getPostById(postId);
            List<Reaction> reactionList = reactionRepository.findAllByPostId(OptionalPost.get().getId());
            if(reactionList.isEmpty()){
                throw new ReactionNotFoundException("No reactions found for post with id: "+postId+"!");
            }

            List<ReactionDto> reactionDto = reactionList.stream()
                            .map(reaction -> new ReactionDto(
                                    reaction.getId(),
                                    reaction.getUsername(),
                                    reaction.getSenderId().getId(),
                                    reaction.getReact()
                            )).collect(Collectors.toList());


            logger.info("Reactions retrieved successfully from post - " + OptionalPost.get().getId());
            return reactionDto;

        }

    @Override
    public void deleteReactionFromPost(Long reactionId) {
        Optional<Reaction> OptionalReaction = reactionRepository.findById(reactionId);
        reactionRepository.delete(OptionalReaction.get());
        logger.info("Reaction deleted successfully from post - " + OptionalReaction.get().getPost().getId());
    }


    @Override
    public Reaction updateReaction(Reaction reaction, Long id) {
        Optional<Reaction> OptionalReaction = reactionRepository.findById(id);
        if(OptionalReaction.isEmpty()){
            throw new ReactionNotFoundException("Reaction with id: "+id+" not found!");
        }
        Reaction updatedReaction = OptionalReaction.get();
        updatedReaction.setReact(reaction.getReact());
        logger.info("Reaction updated successfully - " + updatedReaction.getId());
        return reactionRepository.save(updatedReaction);
    }

     // ---- comment part ----
    @Override
    public void addReactionToComment(Reaction reaction,Authentication authentication) {
        long userId = userService.findUserIdByAuthentication(authentication);
        Optional<Comment> OptionalComment = commentService.getCommentById(reaction.getComment().getId());
        Optional<User> OptionalUser = userService.findById(userId);
        reaction.setComment(OptionalComment.get());
        reaction.setUsername(OptionalUser.get().getFirstName());
        reaction.setSenderId(OptionalUser.get());
        reaction.setFlag(true);
        reactionRepository.save(reaction);
        Notification notification = Notification.builder()
                .sender(OptionalUser.get())
                .receiver(OptionalComment.get().getSenderId())
                .message(OptionalUser.get().getFirstName() + " " + OptionalUser.get().getLastName() + " reacted with " + reaction.getReact() + " to your comment " + OptionalComment.get().getComment())
                .sentTime(LocalDateTime.now())
                .build();
        notificationService.sendNotification(notification,authentication);
        logger.info("Reaction added successfully to comment - " + OptionalComment.get().getId());
    }

    // not tested
    @Override
    public void deleteReactionFromComment(Long reactionId) {

        Optional<Reaction> OptionalReaction = reactionRepository.findById(reactionId);
        reactionRepository.delete(OptionalReaction.get());
        logger.info("Reaction deleted successfully from comment - " + OptionalReaction.get().getComment().getId());

    }

    // not tested
    @Override
    public void updateReactionForComment(Reaction reaction, Long reactionId) {

        Optional<Reaction> OptionalReaction = reactionRepository.findById(reactionId);
        if(OptionalReaction.isEmpty()){
            throw new ReactionNotFoundException("Reaction with id: "+reactionId+" not found!");
        }
        Reaction updatedReaction = OptionalReaction.get();
        updatedReaction.setReact(reaction.getReact());
        logger.info("Reaction updated successfully - " + updatedReaction.getId());
        reactionRepository.save(updatedReaction);


    }

    // not tested
    @Override
    public List<ReactionDto> getReactionsByCommentId(Long commentId) {
        Optional<Comment> OptionalComment = commentService.getCommentById(commentId);
        List<Reaction> reactionList = reactionRepository.findAllByCommentId(commentId);
        if(reactionList.isEmpty()){
            throw new ReactionNotFoundException("No reactions found for post with id: "+commentId+"!");
        }

        List<ReactionDto> reactionDto = reactionList.stream()
                .map(reaction -> new ReactionDto(
                        reaction.getId(),
                        reaction.getUsername(),
                        reaction.getSenderId().getId(),
                        reaction.getReact()
                )).collect(Collectors.toList());
        logger.info("Reactions retrieved successfully from comment - " + OptionalComment.get().getId());
        return reactionDto;
    }

}
