package com.example.facebook.facebook.demo.dto;

import com.example.facebook.facebook.demo.model.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostVideoDto {
    private Long id;
    private byte[] videoData;
}
