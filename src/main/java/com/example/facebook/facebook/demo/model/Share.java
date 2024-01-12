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

    @Column
    private User sharer;

    @Column
    private LocalDateTime dateOfSharing;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"shares"})
    private Post post;

    // todo: add description
    // todo: add count of shares

}
