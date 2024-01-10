package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.model.PostPhoto;
import com.example.facebook.facebook.demo.service.PostPhotoService;
import com.example.facebook.facebook.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PostPhotoServiceImpl implements PostPhotoService {

    private final PostService postService;

    @Override
    public void addPhoto(MultipartFile file, PostPhoto postPhoto) throws IOException {
        byte[] bytes = file.getBytes();
        postPhoto.setPhotoData(bytes);
    }
}
