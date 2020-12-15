package com.cdmservicios.mantenimiento.repositories;

import com.cdmservicios.mantenimiento.models.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, String> {
    @Query("select r from Registro r where r.equipo.code=?1")
    List<Registro> findAllByEquipo(String code);
}
