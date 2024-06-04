package com.example.facebook.facebook.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "text")
    private String about;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "profile_picture_path", nullable = false)
    private String profilePicturePath;


    @Column(name = "cover_picture_path", nullable = false)
        private String coverPicturePath;

    @Digits(integer = 10, fraction = 0)
    @Column
    private String phoneNumber;

    @Column
    private LocalDateTime dateOfCreation = LocalDateTime.now();

    @OneToMany(mappedBy = "page",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("page")
    @EqualsAndHashCode.Exclude
    private Set<Address> addresses;

    @OneToMany(mappedBy = "page",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("page")
    @EqualsAndHashCode.Exclude
    private Set<Post> posts;

    @Column
    private Set<LocalDateTime> businessHours;

    @OneToMany(mappedBy = "page",fetch = FetchType.EAGER)
    private Set<Photo> photos;

    @OneToMany(mappedBy = "page",fetch = FetchType.EAGER)
    private Set<Video> videos;

    @OneToMany(mappedBy = "page", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("page")
    @EqualsAndHashCode.Exclude
    private Set<UserPageRelation> userPageRelations;

   // private Set<Event> events;


}
