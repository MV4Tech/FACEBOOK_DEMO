package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.CommentDto;
import com.example.facebook.facebook.demo.model.Comment;
import com.example.facebook.facebook.demo.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    // add comment
    @PostMapping("/add-comment")
    public ResponseEntity<Void> addComment(@RequestBody @Valid Comment comment, Authentication authentication){
        commentService.addComment(comment,authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // delete comment
    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    // edit comment
    @PutMapping("/edit-comment/{id}")
    public ResponseEntity<Void> editComment(@RequestBody Comment comment, @PathVariable Long id){
        commentService.editComment(comment, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // get all comments by post id
    @GetMapping("/get-comments-by-post-id/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long postId){
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

}
