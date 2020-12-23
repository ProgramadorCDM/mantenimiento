package com.cdmservicios.mantenimiento.repositories;

import com.cdmservicios.mantenimiento.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<Evento, String> {
}
