package com.example.BackEnd.services;

import com.example.BackEnd.models.Livre;
import com.example.BackEnd.repositories.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    public List<Livre> getLivres() {
        return livreRepository.findAll();
    }

    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }

    public Livre ajouterLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public Livre modifierLivre(Long id, Livre livreModifie) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livre introuvable avec l'id : " + id));

        livre.setNom(livreModifie.getNom());
        livre.setDateLecture(livreModifie.getDateLecture());
        livre.setLien(livreModifie.getLien());
        livre.setSites(livreModifie.getSites());
        livre.setLangues(livreModifie.getLangues());
        livre.setChapitresLus(livreModifie.getChapitresLus());
        livre.setNotes(livreModifie.getNotes());

        return livreRepository.save(livre);
    }

    public void supprimerLivre(Long id) {
        livreRepository.deleteById(id);
    }
}
