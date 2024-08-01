package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.Photo;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.PhotoRepository;
import com.example.facebook.facebook.demo.service.PhotoService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final String FOLDER_PATH = "D:\\facebook\\images";
    private final PhotoRepository photoRepository;
    private final UserService userService;

    @Override
    public String addPhoto(MultipartFile file, Authentication authentication, Photo photo) throws IOException {

        String filePaths = FOLDER_PATH + "\\" + file.getOriginalFilename();
        file.transferTo(new File(filePaths));
        Long id = userService.findUserIdByAuthentication(authentication);
        User user = userService.getUserById(id);
        photo.setFilePath(filePaths);
        photo.setUser(user);
        photoRepository.save(photo);
        return "Photo uploaded successfully!";

    }
}
