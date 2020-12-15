package com.cdmservicios.mantenimiento.repositories;

import com.cdmservicios.mantenimiento.models.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotorRepository extends JpaRepository<Motor, String> {

    @Query("select m from Motor m where m.equipo.code =?1")
    Optional<Motor> findMotorByEquipo(String code);
}
