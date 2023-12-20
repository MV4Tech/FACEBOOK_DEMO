package com.example.facebook.facebook.demo.model;

import com.example.facebook.facebook.demo.enums.School;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    @NotNull(message = "Please enter your country")
    @NotBlank(message = "Invalid Name: Empty name")
    private String name;


    @Enumerated(EnumType.STRING)
    @Column
    private School typeOfSchool;


    @Column
    @NotNull(message = "Please enter started date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startedDate;


    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime graduationDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties({"educations"})
    private User user;
}
