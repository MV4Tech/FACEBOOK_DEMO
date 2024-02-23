package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.Photo;
import com.example.facebook.facebook.demo.model.Video;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoService {
    String addVideo(MultipartFile file, Authentication authentication, Video video) throws IOException;
}
