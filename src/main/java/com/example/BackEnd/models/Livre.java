package com.example.BackEnd.models;

import com.example.BackEnd.Views;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livre {
    @Id
    @JsonView(Views.Livres.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonView(Views.Livres.class)
    private String nom;

    @Column
    @JsonView(Views.Livres.class)
    private Date dateLecture;

    @Column
    @JsonView(Views.Livres.class)
    private String lien;

    @ManyToMany(mappedBy = "livres")
    @JsonSerialize(using = SiteListSerializer.class)
    @JsonView(Views.Livres.class)
    private List<Site> sites;

    @Column
    @JsonView(Views.Livres.class)
    private String langues;

    @Column
    @JsonView(Views.Livres.class)
    private int chapitresLus;

    @Column
    @JsonView(Views.Livres.class)
    private int notes;

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> site) {
        this.sites = site;
    }
}