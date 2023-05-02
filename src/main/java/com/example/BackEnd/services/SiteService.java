package com.example.BackEnd.services;

import com.example.BackEnd.models.Livre;
import com.example.BackEnd.models.Site;
import com.example.BackEnd.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteService {
    @Autowired
    private SiteRepository siteRepository;

    public List<Site> getSites() {
        return siteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }


    public Optional<Site> getSiteById(Long id) {
        return siteRepository.findById(id);
    }

    public Site ajouterSite(Site site) {
        return siteRepository.save(site);
    }


    public Site modifierSite(Long id, Site siteModifie) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Site introuvable avec l'id : " + id));
        site.setNom(siteModifie.getNom());
        site.setDomaine(siteModifie.getDomaine());
        return siteRepository.save(site);
    }

    public void supprimerSite(Long id) {
        siteRepository.deleteById(id);
    }
}
