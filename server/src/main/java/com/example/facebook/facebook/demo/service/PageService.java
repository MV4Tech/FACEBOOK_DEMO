package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PageService {
    void addPage(Page page);

    Page getPageById(Long id);

    void deletePage(Long id);

    String setProfileImage(MultipartFile file, Long id) throws IOException;

    byte[] displayProfileImage(long id);

    String setCoverImage(MultipartFile file, long id) throws IOException;

    byte[] displayCoverImage(long id);
}