package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Page;
import com.example.facebook.facebook.demo.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/page")
@RequiredArgsConstructor
public class PageController {
    private final PageService pageService;

    // add new page controller
    // TODO : test this controller + after it test fetching the posts
    @PostMapping("/add-page")
    public ResponseEntity<Void> addPage(@RequestBody Page page){
        pageService.addPage(page);
        return ResponseEntity.ok().build();
    }

    // delete page controller
    @DeleteMapping("/delete-page/{id}")
    public ResponseEntity<Void> deletePage(@PathVariable Long id){
        pageService.deletePage(id);
        return ResponseEntity.noContent().build();
    }

}
