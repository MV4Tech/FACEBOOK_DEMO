package com.example.facebook.facebook.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "Please enter your country")
    @NotBlank(message = "Invalid Name: Empty name")
    private String country;

    @Column
    private String municipality;

    @Column(nullable = false)
    private String city;

    @ManyToOne
    @JsonIgnoreProperties({"addresses"})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

}
