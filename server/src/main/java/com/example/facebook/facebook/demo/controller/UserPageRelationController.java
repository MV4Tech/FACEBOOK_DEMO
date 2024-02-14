package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.UserPageRelationDto;
import com.example.facebook.facebook.demo.model.UserPageRelation;
import com.example.facebook.facebook.demo.service.UserPageRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-page-relation")
public class UserPageRelationController {
    private final UserPageRelationService userPageRelationService;

    // add new user page relation controller
    @PostMapping("/add-new-relation")
    public ResponseEntity<Void> addUserPageRelation(@RequestBody UserPageRelation userPageRelation){
        userPageRelationService.addUserPageRelation(userPageRelation);
        return ResponseEntity.ok().build();
    }

    // remove user page relation controller
    @DeleteMapping("/remove-relation/{userPageRelationId}")
    public ResponseEntity<Void> removeUserPageRelation(@PathVariable Long userPageRelationId){
        userPageRelationService.removeUserPageRelation(userPageRelationId);
        return ResponseEntity.noContent().build();
    }

    // get all user pages by given user id controller
    @GetMapping("/get-all-user-relations-by-user-id/{userId}")
    public ResponseEntity<List<UserPageRelationDto>> getAllUserRelationsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(userPageRelationService.getAllUserRelationsByUserId(userId));
    }

    // get all page users by given page id controller
    @GetMapping("/get-all-page-relations-by-page-id/{pageId}")
    public ResponseEntity<List<UserPageRelationDto>> getAllPageRelationsByPageId(@PathVariable Long pageId){
        return ResponseEntity.ok(userPageRelationService.getAllPageRelationsByPageId(pageId));
    }


    // get the number of the followers of a page
    @GetMapping("/get-followers-count/{pageId}")
    public ResponseEntity<Long> getFollowersCount(@PathVariable Long pageId){
        return ResponseEntity.ok(userPageRelationService.getFollowersCount(pageId));
    }

    // get the number of liked page by a user id
    @GetMapping("/get-liked-page-count/{userId}")
    public ResponseEntity<Long> getLikedPageCount(@PathVariable Long userId){
        return ResponseEntity.ok(userPageRelationService.getLikedPageCount(userId));
    }

}
