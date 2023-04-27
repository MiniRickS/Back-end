package com.example.BackEnd.models;

import com.example.BackEnd.Views;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Site {
    @Id
    @JsonView(Views.Livres.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonView(Views.Livres.class)
    private String nom;

    @Column(nullable = false)
    @JsonView(Views.Livres.class)
    private String domaine;

    @ManyToMany
    @JsonSerialize(using = LivreListSerializer.class)
    @JoinTable(name = "livre_site",
            joinColumns = @JoinColumn(name = "site_id"),
            inverseJoinColumns = @JoinColumn(name = "livre_id"))
    private List<Livre> livres;
}