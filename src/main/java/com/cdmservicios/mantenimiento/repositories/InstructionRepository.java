package com.cdmservicios.mantenimiento.repositories;

import com.cdmservicios.mantenimiento.models.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, String> {
    @Query("select i from Instruction i where i.equipo.code=?1")
    Optional<Instruction> findByEquipo(String code);
}
