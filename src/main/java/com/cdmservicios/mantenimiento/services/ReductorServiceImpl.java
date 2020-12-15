package com.cdmservicios.mantenimiento.services;

import com.cdmservicios.mantenimiento.models.Reductor;
import com.cdmservicios.mantenimiento.repositories.ReductorRepository;
import com.cdmservicios.mantenimiento.services.apis.ReductorServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericServiceImplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReductorServiceImpl extends GenericServiceImplement<Reductor, String> implements ReductorServiceAPI {

    private final ReductorRepository repository;

    public ReductorServiceImpl(ReductorRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Reductor, String> getRepository() {
        return repository;
    }

    @Override
    @Transactional
    public Reductor findByEquipo(String code) {
        Optional<Reductor> optional = repository.findByEquipo(code);
        return optional.orElse(null);
    }
}
