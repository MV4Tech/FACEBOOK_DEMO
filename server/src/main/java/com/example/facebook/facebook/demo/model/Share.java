package com.example.facebook.facebook.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Share{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sharer")
    private User sharer;

    @Column
    private LocalDateTime dateOfSharing = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"shares"})
    private Post post;

    @Column
    private String description;

    @Column
    private int shareCount;



}
