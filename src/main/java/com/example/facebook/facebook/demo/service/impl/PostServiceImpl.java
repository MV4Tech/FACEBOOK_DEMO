package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.PostDto;
import com.example.facebook.facebook.demo.enums.Status;
import com.example.facebook.facebook.demo.exception.PostNotFoundException;
import com.example.facebook.facebook.demo.model.Friendship;
import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.repository.PostRepository;
import com.example.facebook.facebook.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    private final PostRepository postRepository;
    private final FriendshipService friendshipService;



    @Override
    public Optional<Post> getPostById(Long postId) {
         Optional<Post> optionalPost = postRepository.findById(postId);
         if(!optionalPost.isPresent()){
             throw new PostNotFoundException("Post with id - " + postId + " not found");
         }
         return optionalPost;
    }


    // add post method
    @Override
    public void addPost(Post post) {
        postRepository.save(post);
    }


    @Override
    public Set <PostDto> getAllPostsByUserId(Long userId) {
        // Step 1: Fetch posts from the user
        List<Post> userPosts = postRepository.findAllByUserId(userId);

        // Step 2: Fetch friendships where the user is the sender or receiver and the status is accepted
        List<Friendship> friendships = friendshipService.findAllBySenderIdAndStatusOrReceiverIdAndStatus(userId, Status.ACCEPTED, userId, Status.ACCEPTED);

        // Step 3: Retrieve posts from friends and add them to the list of posts
        Set<Post> postsFromFriends = friendships.stream()
                .flatMap(friendship -> Stream.of(friendship.getSender(), friendship.getReceiver()))
                .flatMap(user -> postRepository.findAllByUserId(user.getId()).stream())
                .collect(Collectors.toSet());

        // Combine user posts and posts from friends
        Set<Post> allPosts = new HashSet<>(userPosts);
        allPosts.addAll(userPosts);
        allPosts.addAll(postsFromFriends);

        // Now you can convert allPosts to PostDto as needed
        Set<PostDto> postDtos = allPosts.stream()
                .map(post -> {
                     PostDto postDto = new PostDto();
                     postDto.setUserId(post.getUser().getId());
                     postDto.setFirstName(post.getUser().getFirstName());
                     postDto.setLastName(post.getUser().getLastName());
                     postDto.setPostId(post.getId());
                     postDto.setHead(post.getPostHead());
                     postDto.setDescription(post.getDescription());
                     postDto.setDateOfPosting(post.getDateOfPosting());
                    return postDto;
                })
                .collect(Collectors.toSet());

        return postDtos;
    }

    @Override
    public void deletePost(Long postId) {
         postRepository.delete(getPostById(postId).get());
            logger.info("Post deleted successfully with id - " + postId);
    }

    @Override
    public void editPost(Post post, Long postId) {
        Post postToEdit = getPostById(postId).get();
        postToEdit.setPostHead(post.getPostHead());
        postToEdit.setDescription(post.getDescription());
        postRepository.save(postToEdit);
        logger.info("Post edited successfully with id - " + postId);
    }

}
