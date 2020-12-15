package com.cdmservicios.mantenimiento.repositories;

import com.cdmservicios.mantenimiento.models.Reductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReductorRepository extends JpaRepository<Reductor, String> {

    @Query("select r from Reductor r where r.equipo.code =?1 ")
    Optional<Reductor> findByEquipo(String code);
}
