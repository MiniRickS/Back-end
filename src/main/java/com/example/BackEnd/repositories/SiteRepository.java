package com.example.BackEnd.repositories;

import com.example.BackEnd.models.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SiteRepository extends JpaRepository<Site, Long> {

    Optional<Site> findByDomaine(String domaine);


}
