package com.cdmservicios.mantenimiento.repositories;

import com.cdmservicios.mantenimiento.models.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorRepository extends JpaRepository<Motor, String> {
}
