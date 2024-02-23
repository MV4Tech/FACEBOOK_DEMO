package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.Photo;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.model.Video;
import com.example.facebook.facebook.demo.repository.VideoRepository;
import com.example.facebook.facebook.demo.service.UserService;
import com.example.facebook.facebook.demo.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final String FOLDER_PATH = "D:\\facebook\\videos";
    private final VideoRepository videoRepository;
    private final UserService userService;

    @Override
    public String addVideo(MultipartFile file, Authentication authentication, Video video) throws IOException {
        String filePaths = FOLDER_PATH + "\\" + file.getOriginalFilename();
        file.transferTo(new File(filePaths));
        Long id = userService.findUserIdByAuthentication(authentication);
        User user = userService.getUserById(id);
        video.setVideoFilePath(filePaths);
        video.setUser(user);
        videoRepository.save(video);
        return "Video uploaded successfully!";
    }

    // add show video i dont know how this is gonna look like
}
