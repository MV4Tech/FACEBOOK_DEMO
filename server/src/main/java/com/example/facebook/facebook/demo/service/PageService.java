package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PageService {
    Long findPageIdByAuthentication(Authentication authentication);
    void addPage(Page page, Authentication authentication);

    Page getPageById(Long id);

    void deletePage(Long id);

    String setProfileImage(MultipartFile file,Long pageId) throws IOException;
//
    byte[] displayProfileImage(long id) throws IOException;

   String setCoverImage(MultipartFile file, long pageId) throws IOException;
//
    byte[] displayCoverImage(long pageId) throws IOException;

    Page getPageByOwnerId(Long id);
}
