package com.example.facebook.facebook.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private Long id;
    private String comment;
    private LocalDateTime dateOfComment;
    private Long senderId;
    private String username;
}
