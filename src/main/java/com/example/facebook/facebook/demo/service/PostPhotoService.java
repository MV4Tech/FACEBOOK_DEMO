package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.PostPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

public interface PostPhotoService {


    void addPhoto(MultipartFile file, PostPhoto postPhoto) throws IOException;
}
