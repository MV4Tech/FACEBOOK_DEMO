package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Comment;
import com.example.facebook.facebook.demo.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    // add comment
    @PostMapping("/add-comment/{postId}/{userId}")
    public ResponseEntity<Void> addComment(@RequestBody @Valid Comment comment, @PathVariable Long postId, @PathVariable Long userId){
        commentService.addComment(comment, postId,userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // delete comment
    // TODO - TEST
    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

}
