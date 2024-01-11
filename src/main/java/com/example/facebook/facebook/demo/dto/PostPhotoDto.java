package com.example.facebook.facebook.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPhotoDto {
    private Long id;
    private byte[] photoData;
}
