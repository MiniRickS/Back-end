package com.example.BackEnd.controllers;

import com.example.BackEnd.Views;
import com.example.BackEnd.models.Livre;
import com.example.BackEnd.models.Site;
import com.example.BackEnd.services.LivreService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BackEnd.repositories.LivreRepository;
import com.example.BackEnd.repositories.SiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    @Autowired
    private SiteRepository siteRepository;
    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private LivreService livreService;
    @GetMapping
    @JsonView(Views.Livres.class)
    public List<Livre> getLivres() {
        return livreService.getLivres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long id) {
        Optional<Livre> livre = livreService.getLivreById(id);

        if (livre.isPresent()) {
            return ResponseEntity.ok(livre.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Livre ajouterLivre(@Valid @RequestBody Livre livre) {
        List<Site> sites = livre.getSites();
        List<Site> existingSites = new ArrayList<>();

        if (sites != null) {
            for (Site site : sites) {
                Optional<Site> existingSite = siteRepository.findByDomaine(site.getDomaine());
                if (existingSite.isPresent()) {
                    existingSites.add(existingSite.get());
                } else {
                    Site newSite = new Site();
                    newSite.setNom(site.getNom());
                    newSite.setDomaine(site.getDomaine());
                    newSite.setLivres(new ArrayList<>());
                    existingSites.add(newSite);
                }
            }
        }

        livre.setSites(existingSites);

        Livre savedLivre = livreService.ajouterLivre(livre);

        // Update and save the sites with the saved Livre instance
        for (Site site : existingSites) {
            site.getLivres().add(savedLivre);
            siteRepository.save(site);
        }

        return savedLivre;
    }


    @PutMapping("/{id}")
    public ResponseEntity<Livre> modifierLivre(@PathVariable Long id, @Valid @RequestBody Livre livreModifie) {
        Livre livre = livreService.getLivreById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livre introuvable avec l'id : " + id));

        livre.setNom(livreModifie.getNom());
        livre.setDateLecture(livreModifie.getDateLecture());
        livre.setLien(livreModifie.getLien());
        livre.setLangues(livreModifie.getLangues());
        livre.setChapitresLus(livreModifie.getChapitresLus());
        livre.setNotes(livreModifie.getNotes());

        List<Site> sites = livreModifie.getSites();
        List<Site> existingSites = new ArrayList<>();
        if (sites != null) {
            for (Site site : sites) {
                Optional<Site> existingSite = siteRepository.findByDomaine(site.getDomaine());
                if (existingSite.isPresent()) {
                    existingSites.add(existingSite.get());
                } else {
                    Site newSite = new Site();
                    newSite.setNom(site.getNom());
                    newSite.setDomaine(site.getDomaine());
                    newSite.setLivres(new ArrayList<>());
                    existingSites.add(siteRepository.save(newSite));
                }
            }
        }

        // Remove old Site-Livre associations
        for (Site site : livre.getSites()) {
            site.getLivres().remove(livre);
            siteRepository.save(site);
        }

        // Set new Site-Livre associations
        livre.setSites(existingSites);
        for (Site site : existingSites) {
            site.getLivres().add(livre);
            siteRepository.save(site);
        }

        livre = livreService.modifierLivre(id, livre);

        return ResponseEntity.ok(livre);
    }




    @DeleteMapping("/{id}")
    public void supprimerLivre(@PathVariable Long id) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livre introuvable avec l'id : " + id));

        List<Site> sites = livre.getSites();
        for (Site site : sites) {
            site.getLivres().remove(livre);
        }
        livre.setSites(null);

        livreRepository.deleteById(id);
    }

}
