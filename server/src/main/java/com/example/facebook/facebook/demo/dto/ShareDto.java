package com.example.facebook.facebook.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShareDto {
    private Long id;
    private Long sharerId;
    private Long postId;
    private String description;
    private int shareCount;
    private String dateOfSharing;
}
