package com.example.facebook.facebook.demo.dto;

import com.example.facebook.facebook.demo.model.Comment;
import com.example.facebook.facebook.demo.model.PostPhoto;
import com.example.facebook.facebook.demo.model.PostVideo;
import com.example.facebook.facebook.demo.model.Reaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long postId;
    private String head;
    private String description;
    private LocalDateTime dateOfPosting;
    private String firstName;
    private String lastName;
    private Long userId;

}
