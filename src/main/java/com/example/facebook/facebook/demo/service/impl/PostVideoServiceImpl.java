package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.PostVideoDto;
import com.example.facebook.facebook.demo.exception.PostVideoNotFoundException;
import com.example.facebook.facebook.demo.model.PostVideo;
import com.example.facebook.facebook.demo.repository.PostVideoRepository;
import com.example.facebook.facebook.demo.service.PostVideoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class PostVideoServiceImpl implements PostVideoService {

    private static final Logger logger = LoggerFactory.getLogger(PostVideoServiceImpl.class);
    private final PostVideoRepository postVideoRepository;
    @Override
    public void addVideo(MultipartFile file, PostVideo postVideo) throws IOException {
        byte[] bytes = file.getBytes();
        postVideo.setVideoData(bytes);
        postVideo.setPost(postVideo.getPost());
        postVideoRepository.save(postVideo);
        logger.info("Video added successfully! to post - "+postVideo.getPost().getId());
    }

    @Override
    public List<PostVideoDto> displayVideo(Long postId) {
        List<PostVideo> postVideos = postVideoRepository.findAllByPostId(postId);
        if(postVideos.isEmpty()){
            throw new PostVideoNotFoundException("No video/s found!");
        }
        List<PostVideoDto> postVideoDto = postVideos.stream().map(postVideo ->{
            PostVideoDto postVideoDto1 = new PostVideoDto();
            postVideoDto1.setId(postVideo.getId());
            postVideoDto1.setVideoData(postVideo.getVideoData());
            return postVideoDto1;
        }).collect(toList());
        logger.info("Video/s displayed successfully! with post id - "+postId+"!");
        return postVideoDto;
    }

    @Override
    public void deleteVideo(Long videoId) {
        if(!postVideoRepository.existsById(videoId)){
            throw new PostVideoNotFoundException("Video with id - "+videoId+" not found!");
        }
        postVideoRepository.deleteById(videoId);
        logger.info("Video deleted successfully! with id - "+videoId+"!");
    }


}
