package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.ShareDto;
import com.example.facebook.facebook.demo.model.Share;
import com.example.facebook.facebook.demo.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/share")
@RequiredArgsConstructor
public class ShareController {
    private final ShareService shareService;

    // share post
    @PostMapping("/share-post")
    public ResponseEntity<Void> sharePost(@RequestBody Share share, Authentication authentication){
        shareService.sharePost(share,authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // not sure if this is the correct implementation
    @GetMapping("/display-share/{shareId}")
    public ResponseEntity<ShareDto> displayShare(@PathVariable Long shareId){
        return ResponseEntity.ok(shareService.displayShare(shareId));
    }

    @GetMapping("/get-share-count/{postId}")
    ResponseEntity<Integer> getShareCount(@PathVariable Long postId){
        return ResponseEntity.ok(shareService.getShareCount(postId));
    }

}
