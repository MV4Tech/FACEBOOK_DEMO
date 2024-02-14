package com.example.facebook.facebook.demo.dto;

import com.example.facebook.facebook.demo.enums.TypeReaction;
import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReactionPostDto {
    private Long id;
    private TypeReaction react;
    private User user_id;
    private Post post;
}
