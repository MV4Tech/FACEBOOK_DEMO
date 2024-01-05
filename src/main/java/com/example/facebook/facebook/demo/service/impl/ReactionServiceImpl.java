package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.ReactionDto;
import com.example.facebook.facebook.demo.exception.PostNotFoundException;
import com.example.facebook.facebook.demo.exception.ReactionNotFoundException;
import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.model.Reaction;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.ReactionRepository;
import com.example.facebook.facebook.demo.service.PostService;
import com.example.facebook.facebook.demo.service.ReactionService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    @Override
    public void addReactionToPost(Reaction reaction, Long postId, Long userId) {
        Optional<Post> OptionalPost = postService.getPostById(postId);
        Optional<User> OptionalUser = userService.findById(userId);
        reaction.setPost(OptionalPost.get());
        reaction.setUsername(OptionalUser.get().getFirstName());
        reaction.setFlag(true);
        reactionRepository.save(reaction);
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

}
