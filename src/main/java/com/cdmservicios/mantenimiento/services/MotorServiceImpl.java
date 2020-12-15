package com.cdmservicios.mantenimiento.services;

import com.cdmservicios.mantenimiento.models.Motor;
import com.cdmservicios.mantenimiento.repositories.MotorRepository;
import com.cdmservicios.mantenimiento.services.apis.MotorServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericServiceImplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MotorServiceImpl extends GenericServiceImplement<Motor,String> implements MotorServiceAPI {

    private final MotorRepository repository;

    public MotorServiceImpl(MotorRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Motor, String> getRepository() {
        return repository;
    }

    @Override
    @Transactional
    public Motor findMotorByEquipo(String code) {
        Optional<Motor> optional = repository.findMotorByEquipo(code);
        return optional.orElse(null);
    }
}
