package com.example.facebook.facebook.demo.service.impl;


import com.example.facebook.facebook.demo.dto.PostPhotoDto;
import com.example.facebook.facebook.demo.model.PostPhoto;
import com.example.facebook.facebook.demo.repository.PostPhotoRepository;
import com.example.facebook.facebook.demo.service.PostPhotoService;
import com.example.facebook.facebook.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostPhotoServiceImpl implements PostPhotoService {

    private final PostService postService;
    private final PostPhotoRepository postPhotoRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostPhotoServiceImpl.class);
    @Override
    public void addPhoto(MultipartFile file, PostPhoto postPhoto) throws IOException {
        byte[] bytes = file.getBytes();
        postPhoto.setPhotoData(bytes);
        logger.info("Photo added successfully! to post - "+postPhoto.getPost().getId());
    }

    @Override
    public List<PostPhotoDto> displayPhoto(Long postId) {
        List<PostPhoto> postPhotos = postPhotoRepository.findAllByPostId(postId);
        if(postPhotos.isEmpty()){
            throw new RuntimeException("No photo found!");
        }
        List<PostPhotoDto> postPhotoDto = postPhotos.stream().map(postPhoto ->{
            PostPhotoDto postPhotoDto1 = new PostPhotoDto();
            postPhotoDto1.setId(postPhoto.getId());
            postPhotoDto1.setPhotoData(postPhoto.getPhotoData());
            return postPhotoDto1;
        }).collect(Collectors.toList());
        logger.info("Photo/s displayed successfully! with post id - "+postId+"!");
        return postPhotoDto;
    }

    @Override
    public void deletePhoto(Long videoId) {
        postPhotoRepository.deleteById(videoId);
        logger.info("Photo deleted successfully! with id - "+videoId+"!");
    }
}
