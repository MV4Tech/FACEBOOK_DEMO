package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Reaction;
import com.example.facebook.facebook.demo.service.ReactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reaction")
public class ReactionController {

    private final ReactionService reactionService;

    @PostMapping("/add-reaction-to-post/{postId}/{userId}")
    public ResponseEntity<Void> addReactionToPost(@RequestBody @Valid Reaction reaction, @PathVariable Long postId,@PathVariable Long userId){
        reactionService.addReactionToPost(reaction,postId,userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
