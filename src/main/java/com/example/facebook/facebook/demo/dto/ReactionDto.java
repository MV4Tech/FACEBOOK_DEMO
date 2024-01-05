package com.example.facebook.facebook.demo.dto;

import com.example.facebook.facebook.demo.enums.TypeReaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReactionDto {
    private Long id;
    private String username;
    private TypeReaction react;
}
