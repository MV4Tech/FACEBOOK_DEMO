package com.example.facebook.facebook.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {

    private int id;
    private String title;
    private String description;
    private List<ReactionDto> reactions;


}
