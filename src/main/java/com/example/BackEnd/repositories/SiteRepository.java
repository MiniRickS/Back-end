package com.example.BackEnd.repositories;

import com.example.BackEnd.models.Livre;
import com.example.BackEnd.models.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SiteRepository extends JpaRepository<Site, Long> {

    Optional<Site> findByDomaine(String domaine);
    @Query("SELECT s.livres FROM Site s WHERE s.id = :siteId")
    List<Livre> findLivresBySiteId(@Param("siteId") Long siteId);

}
