package com.cdmservicios.mantenimiento.repositories;

import com.cdmservicios.mantenimiento.models.Lubricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LubricanteRepository extends JpaRepository<Lubricante, String> {

    @Query("select l from Lubricante l where l.equipo.code = ?1")
    List<Lubricante> findByEquipo(String code);
}
