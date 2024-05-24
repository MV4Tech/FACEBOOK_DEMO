package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.PostDto;
import com.example.facebook.facebook.demo.enums.Status;
import com.example.facebook.facebook.demo.exception.PostNotFoundException;
import com.example.facebook.facebook.demo.model.Friendship;
import com.example.facebook.facebook.demo.model.Page;
import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.PostRepository;
import com.example.facebook.facebook.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
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
    private final UserPageRelationService userPageRelationService;
    private final UserService userService;
    private final PageService pageService;


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
    public PostDto addPost(Post post, Authentication authentication) {
        long userId = userService.findUserIdByAuthentication(authentication);
        User user = userService.getUserById(userId);
        Post newPost = Post.builder()
                        .postHead(post.getPostHead())
                        .dateOfPosting(post.getDateOfPosting())
                        .description(post.getDescription())
                        .user(user)
                        .build();
        postRepository.save(newPost);
        logger.info("Post added successfully with id - " + post.getId() + " by user id - "+ userId);
        return PostDto.builder()
                .postId(newPost.getId())
                .userId(newPost.getUser().getId())
                .firstName(newPost.getUser().getFirstName())
                .lastName(newPost.getUser().getLastName())
                .head(newPost.getPostHead())
                .description(newPost.getDescription())
                .dateOfPosting(newPost.getDateOfPosting())
                .build();
    }


    @Override
    public Set <PostDto> getAllPostsByUserId(Authentication authentication) {
        User currUser = (User) authentication.getPrincipal();
        long userId = currUser.getId();
        // Step 1: Fetch posts from the user
        List<Post> userPosts = postRepository.findAllByUserId(userId);

        // Step 2: Fetch friendships where the user is the sender or receiver and the status is accepted
        List<Friendship> friendships = friendshipService.findAllBySenderIdAndStatusOrReceiverIdAndStatus(userId, Status.ACCEPTED, userId, Status.ACCEPTED);

        // Step 3: Retrieve posts from friends and add them to the list of posts
        Set<Post> postsFromFriends = friendships.stream()
                .flatMap(friendship -> Stream.of(friendship.getSender(), friendship.getReceiver()))
                .flatMap(user -> postRepository.findAllByUserId(user.getId()).stream())
                .collect(Collectors.toSet());
        // step 4: Combine all of these with the user page relations posts

        List<Page> pages = userPageRelationService.getAllPagesByUserId(userId);
        Set<Post> postsFromPages = pages.stream().flatMap(page -> postRepository.findAllByPageId(page.getId())
                .stream())
                .collect(Collectors.toSet());

        // Combine user posts and posts from friends
        Set<Post> allPosts = new HashSet<>(userPosts);
        allPosts.addAll(userPosts);
        allPosts.addAll(postsFromFriends);
        allPosts.addAll(postsFromPages);

        // Now you can convert allPosts to PostDto as needed
        Set<PostDto> postDtos = allPosts.stream()
                .map(post -> {
                    PostDto postDto = new PostDto();
                    if(post.getUser() == null){
                        postDto.setPageId(post.getPage().getId());
                        postDto.setPageName(post.getPage().getName());
                    }else{
                        postDto.setUserId(post.getUser().getId());
                        postDto.setFirstName(post.getUser().getFirstName());
                        postDto.setLastName(post.getUser().getLastName());
                    }
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
    public PostDto deletePost(Long postId, Authentication authentication) {

        Post post = getPostById(postId).get();
        if(!isPostOwner(authentication,postId)) {
            throw new PostNotFoundException("Post with id - " + postId + " not found");
        }

         postRepository.delete(post);
            logger.info("Post deleted successfully with id - " + postId);
            return PostDto.builder()
                    .postId(post.getId())
                    .userId(post.getUser().getId())
                    .firstName(post.getUser().getFirstName())
                    .lastName(post.getUser().getLastName())
                    .head(post.getPostHead())
                    .description(post.getDescription())
                    .dateOfPosting(post.getDateOfPosting())
                    .build();
    }

    @Override
    public PostDto editPost(Post post, Long postId, Authentication authentication) {
        if(!isPostOwner(authentication,postId)) {
            throw new PostNotFoundException("Post with id - " + postId + " not found");
        }
        Post postToEdit = getPostById(postId).get();
        postToEdit.setPostHead(post.getPostHead());
        postToEdit.setDescription(post.getDescription());
        postRepository.save(postToEdit);
        logger.info("Post edited successfully with id - " + postId);
        return PostDto.builder()
                .postId(postToEdit.getId())
                .userId(postToEdit.getUser().getId())
                .firstName(postToEdit.getUser().getFirstName())
                .lastName(postToEdit.getUser().getLastName())
                .head(postToEdit.getPostHead())
                .description(postToEdit.getDescription())
                .dateOfPosting(postToEdit.getDateOfPosting())
                .build();
    }

    @Override
    public void addPostPage(Post post, Authentication authentication) {

        User pageOwner = (User) authentication.getPrincipal();
        Page page = pageService.getPageByOwnerId(pageOwner.getId());
        post.setPage(page);
        postRepository.save(post);
        logger.info("Post added successfully with id - " + post.getId() + " by page id - " + post.getPage().getId());
    }

    @Override
    public Set<Post> getAllPostsByPageId(Long pageId) {
        return postRepository.findAllByPageId(pageId);
    }

    public Boolean isPostOwner(Authentication authentication, Long postId){
        long userId = userService.findUserIdByAuthentication(authentication);
        User user = userService.getUserById(userId);
        List<Long> postIds = postRepository.findAllByUserId(userId).stream().map(Post::getId).collect(Collectors.toList());
        return postIds.contains(postId);
    }

}
