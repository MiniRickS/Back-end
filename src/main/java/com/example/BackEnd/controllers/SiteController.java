package com.example.BackEnd.controllers;

import com.example.BackEnd.models.Livre;
import com.example.BackEnd.models.Site;
import com.example.BackEnd.repositories.LivreRepository;
import com.example.BackEnd.repositories.SiteRepository;
import com.example.BackEnd.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@CrossOrigin(origins = "http://localhost:4200")
public class SiteController {
    @Autowired
    private SiteService siteService;
    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private LivreRepository livreRepository;

    @GetMapping
    public List<Site> getSites() {
        List<Site> sites = siteRepository.findAll();
        for (Site site : sites) {
            List<Livre> livres = livreRepository.findLivresBySiteId(site.getId());
            site.setLivres(livres);
        }
        return sites;
    }

    @PostMapping
    public Site ajouterSite(@RequestBody Site site) {
        return siteService.ajouterSite(site);
    }

    @PutMapping("/{id}")
    public Site modifierSite(@PathVariable Long id, @RequestBody Site siteModifie) {
        return siteService.modifierSite(id, siteModifie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerSite(@PathVariable Long id) {
        siteService.supprimerSite(id);
        return ResponseEntity.noContent().build();
    }
}
