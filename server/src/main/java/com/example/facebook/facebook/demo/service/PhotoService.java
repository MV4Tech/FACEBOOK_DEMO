package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.Photo;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {
    String addPhoto(MultipartFile file, Authentication authentication, Photo photo) throws IOException;
}
