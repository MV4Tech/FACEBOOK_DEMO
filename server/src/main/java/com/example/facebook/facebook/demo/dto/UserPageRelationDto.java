package com.example.facebook.facebook.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPageRelationDto {
    private Long id;
    private String pageName;
    private String userName;
    private LocalDateTime dateOfRelation;

}
