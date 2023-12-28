package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.exception.PostNotFoundException;
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

import java.util.Optional;

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
        if(!OptionalPost.isPresent()){
            throw new PostNotFoundException("Post with id - " + postId + " not found");
        }

        if(reaction.getFlag()==false){
            User user = userService.getUserById(userId);
            reaction.setUsername(user.getFirstName());
            reaction.setPost(OptionalPost.get());

            reactionRepository.save(reaction);
            logger.info("Reaction added to post with id - "+postId);
        }else{
            reactionRepository.delete(reaction);
            logger.info("Reaction deleted from post with id - "+postId +"for user with id - " + userId);
        }

     
    }

}
