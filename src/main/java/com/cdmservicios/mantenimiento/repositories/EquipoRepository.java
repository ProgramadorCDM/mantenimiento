package com.cdmservicios.mantenimiento.repositories;

import com.cdmservicios.mantenimiento.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, String> {
}
