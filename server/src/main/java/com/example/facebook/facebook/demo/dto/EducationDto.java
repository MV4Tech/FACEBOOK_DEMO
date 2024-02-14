package com.example.facebook.facebook.demo.dto;

import com.example.facebook.facebook.demo.enums.School;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {

    private Long id;
    private String name;
    private School typeOfSchool;
    private LocalDateTime startedDate;
    private LocalDateTime graduationDate;

}
