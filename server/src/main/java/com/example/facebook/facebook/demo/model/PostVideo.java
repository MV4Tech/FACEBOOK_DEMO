package com.example.facebook.facebook.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIgnoreProperties({"videos"})
    private Post post;

    @Column(name = "video_file_path", nullable = false)
    private String videoFilePath;

    @Column(name = "description")
    private String description;
}
