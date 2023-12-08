package com.example.facebook.facebook.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String country;

    @Column
    private String municipality;

    @Column(nullable = false)
    private String city;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
