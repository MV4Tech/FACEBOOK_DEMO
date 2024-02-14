package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.PostVideoDto;
import com.example.facebook.facebook.demo.model.PostVideo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostVideoService {

    void addVideo(MultipartFile file, PostVideo postVideo) throws IOException;

    List<PostVideoDto> displayVideo(Long postId);

    void deleteVideo(Long videoId);
}
