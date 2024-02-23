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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostPhotoServiceImpl implements PostPhotoService {

    private final String FOLDER_PATH = "D:\\facebook\\images";

    //private final PostService postService;
    private final PostPhotoRepository postPhotoRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostPhotoServiceImpl.class);
    @Override
    public void addPhoto(MultipartFile file, PostPhoto postPhoto) throws IOException {
        String filePaths = FOLDER_PATH + "\\" + file.getOriginalFilename();
        file.transferTo(new File(filePaths));
        postPhoto.setPost(postPhoto.getPost());
        postPhoto.setPhotoFilePath(filePaths);
        postPhotoRepository.save(postPhoto);
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
            String filePath = postPhoto.getPhotoFilePath();
            postPhotoDto1.setId(postPhoto.getId());
            try {
                postPhotoDto1.setPhotoData(Files.readAllBytes(new File(filePath).toPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return postPhotoDto1;
        }).collect(Collectors.toList());
        logger.info("Photo/s displayed successfully! with post id - "+postId+"!");
        return postPhotoDto;
    }

    @Override
    public void deletePhoto(Long photoId) {
        if(!postPhotoRepository.existsById(photoId)){
            throw new RuntimeException("Photo not found!");
        }
        postPhotoRepository.deleteById(photoId);
        logger.info("Photo deleted successfully! with id - "+photoId+"!");
    }
}
