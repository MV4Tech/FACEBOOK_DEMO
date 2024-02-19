package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.exception.PageNotFoundException;
import com.example.facebook.facebook.demo.exception.UserNotFoundException;
import com.example.facebook.facebook.demo.model.Page;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.model.UserPageRelation;
import com.example.facebook.facebook.demo.repository.PageRepository;
import com.example.facebook.facebook.demo.service.PageService;
import com.example.facebook.facebook.demo.service.UserPageRelationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final PageRepository pageRepository;
    private final UserPageRelationService userPageRelationService;
    private static final Logger logger = LoggerFactory.getLogger(PageServiceImpl.class);

    @Override
    public Long findPageIdByAuthentication(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return user.getId();
    }

    @Override
    public void addPage(Page page) {
        pageRepository.save(page);
        UserPageRelation userPageRelation = UserPageRelation.builder()
                .page(page)
                .user(page.getOwner())
                .build();
        userPageRelationService.addUserPageRelation(userPageRelation);
        logger.info("Page added successfully with id" + page.getId());
    }

    @Override
    public Page getPageById(Long id) {
        Optional<Page> page = pageRepository.findById(id);
        if(!page.isPresent()){
            throw new PageNotFoundException("Page not found with id " + id);
        }

        return page.get();
    }

    @Override
    public void deletePage(Long id) {
        Page page = getPageById(id);
        pageRepository.delete(page);
        logger.info("Page deleted successfully with id" + id);
    }

    @Override
    public String setProfileImage(MultipartFile file, Long id) throws IOException {

        byte[] bytes = file.getBytes();
       Page page = getPageById(id);
        page.setProfilePicture(bytes);
        pageRepository.save(page);
        return "Profile picture uploaded successfully!";

    }

    @Override
    public byte[] displayProfileImage(long id) {
        Page page = getPageById(id);
        byte[] image = page.getProfilePicture();
        return image;
    }

    @Override
    public String setCoverImage(MultipartFile file, long id) throws IOException {
        byte[] bytes = file.getBytes();
        Page page = getPageById(id);
        page.setCoverPhoto(bytes);
        pageRepository.save(page);
        return "Cover picture uploaded successfully!";
    }

    @Override
    public byte[] displayCoverImage(long id) {
        Page page = getPageById(id);
        byte[] image = page.getCoverPhoto();
        return image;
    }


}
