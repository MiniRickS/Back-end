package com.example.BackEnd.models;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column
    private Date dateLecture;

    @Column
    private String lien;

    @ManyToMany(mappedBy = "livres")
    @JsonSerialize(using = SiteListSerializer.class)
    private List<Site> sites;

    @Column
    private String langues;

    @Column
    private int chapitresLus;

    @Column
    private int notes;

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> site) {
        this.sites = site;
    }
}