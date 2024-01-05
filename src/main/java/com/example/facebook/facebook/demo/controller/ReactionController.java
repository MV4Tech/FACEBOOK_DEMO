package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.ReactionDto;
import com.example.facebook.facebook.demo.model.Reaction;
import com.example.facebook.facebook.demo.service.ReactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reaction")
public class ReactionController {

    private final ReactionService reactionService;

    // add reaction to post
    @PostMapping("/add-reaction-to-post/{postId}/{userId}")
    public ResponseEntity<Void> addReactionToPost(@RequestBody @Valid Reaction reaction, @PathVariable Long postId,@PathVariable Long userId){
        reactionService.addReactionToPost(reaction,postId,userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // delete reaction from post
    @DeleteMapping("/delete-reaction-from-post/{reactionId}")
    public ResponseEntity<Void> deleteReactionFromPost(@PathVariable Long reactionId){
        reactionService.deleteReactionFromPost(reactionId);
        return ResponseEntity.noContent().build();
    }

    // get all reactions by post id
    @GetMapping("/get-reactions-by-post-id/{postId}")
    public ResponseEntity<List<ReactionDto>> getReactionsByPostId(@PathVariable Long postId){
        return ResponseEntity.ok(reactionService.getReactionsByPostId(postId));
    }
    

    // update reaction
    @PutMapping("/update-reaction/{id}")
    public ResponseEntity<Reaction> updateReaction(@RequestBody Reaction reaction, @PathVariable Long id){
       return ResponseEntity.ok(reactionService.updateReaction(reaction,id));
    }




}
