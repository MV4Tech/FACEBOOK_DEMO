package com.example.facebook.facebook.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String postHead;

    @Column(name = "description")
    private String description;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfPosting = LocalDateTime.now();

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("post")
    @EqualsAndHashCode.Exclude
    private Set<Reaction> likes;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("post")
    @EqualsAndHashCode.Exclude
    private Set<Comment> comments;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("post")
    @EqualsAndHashCode.Exclude
    private Set<Share> shares;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("post")
    @EqualsAndHashCode.Exclude
    private Set<PostPhoto> photos;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("post")
    @EqualsAndHashCode.Exclude
    private Set<PostVideo> videos;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"feed"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

}
