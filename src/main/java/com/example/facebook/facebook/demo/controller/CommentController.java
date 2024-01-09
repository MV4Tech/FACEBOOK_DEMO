package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.CommentDto;
import com.example.facebook.facebook.demo.model.Comment;
import com.example.facebook.facebook.demo.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // edit comment
    // TODO da ne vrusha comment!
    @PutMapping("/edit-comment/{id}")
    public ResponseEntity<Comment> editComment(@RequestBody Comment comment, @PathVariable Long id){
        return ResponseEntity.ok(commentService.editComment(comment,id));
    }

    // get all comments by post id
    @GetMapping("/get-comments-by-post-id/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long postId){
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

}
