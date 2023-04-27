package com.example.BackEnd.repositories;

import com.example.BackEnd.models.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    @Query("SELECT l FROM Livre l JOIN l.sites s WHERE s.id = :siteId")
    List<Livre> findLivresBySiteId(@Param("siteId") Long siteId);
}
