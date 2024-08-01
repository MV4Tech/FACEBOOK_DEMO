package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Page;
import com.example.facebook.facebook.demo.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/page")
@RequiredArgsConstructor
public class PageController {
    private final PageService pageService;

    // add new page controller
    @PostMapping("/add-page")
    public ResponseEntity<Void> addPage(@RequestBody Page page, Authentication authentication){
        pageService.addPage(page,authentication);
        return ResponseEntity.ok().build();
    }

    // delete page controller
    // we cant, and we don't want to delete our page
    @DeleteMapping("/delete-page/{id}")
    public ResponseEntity<Void> deletePage(@PathVariable Long id){
        pageService.deletePage(id);
        return ResponseEntity.noContent().build();
    }

    // add profile picture controller
    @PostMapping("/add-profile-picture/{pageId}")
    public ResponseEntity<?> addProfileImage(@RequestParam("image") MultipartFile file, @PathVariable("pageId") Long pageId) throws IOException {
        String uploadMessage = pageService.setProfileImage(file,pageId);

        return ResponseEntity.status(HttpStatus.OK).body(uploadMessage);
    }

    // display profile picture controller
    @GetMapping("/display-profile-picture/{pageId}")
    public ResponseEntity<byte[]> displayProfileImage(@PathVariable("pageId") long pageId) throws IOException {
        byte[] imageBytes = pageService.displayProfileImage(pageId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    // add cover picture controller
    @PostMapping("/add-cover-picture/{pageId}")
    public ResponseEntity<?> addCoverImage(@RequestParam("image") MultipartFile file,@PathVariable long pageId) throws IOException {
        String uploadMessage = pageService.setCoverImage(file,pageId);

        return ResponseEntity.status(HttpStatus.OK).body(uploadMessage);
    }

    // display cover picture controller
    @GetMapping("/display-cover-picture/{pageId}")
    public ResponseEntity<byte[]> displayCover(@PathVariable("pageId") long pageId) throws IOException {
        byte[] imageBytes = pageService.displayCoverImage(pageId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }


}
