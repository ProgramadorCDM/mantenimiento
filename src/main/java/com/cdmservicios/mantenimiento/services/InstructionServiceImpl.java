package com.cdmservicios.mantenimiento.services;

import com.cdmservicios.mantenimiento.models.Instruction;
import com.cdmservicios.mantenimiento.repositories.InstructionRepository;
import com.cdmservicios.mantenimiento.services.apis.InstructionServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericServiceImplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InstructionServiceImpl extends GenericServiceImplement<Instruction, String> implements InstructionServiceAPI {
    private final InstructionRepository repository;

    public InstructionServiceImpl(InstructionRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Instruction, String> getRepository() {
        return repository;
    }

    @Transactional
    @Override
    public Instruction findByEquipo(String code) {
        Optional<Instruction> optional = repository.findByEquipo(code);
        return optional.orElse(null);
    }
}
