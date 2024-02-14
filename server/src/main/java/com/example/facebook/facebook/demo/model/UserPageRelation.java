package com.example.facebook.facebook.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPageRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"userPageRelations"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "page_id")
    @JsonIgnoreProperties({"userPageRelations"})
    private Page page;

    @Column(nullable = false)
    private LocalDateTime dateStartedFollowing = LocalDateTime.now();
}
