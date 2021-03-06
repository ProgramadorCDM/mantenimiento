package com.cdmservicios.mantenimiento.services;

import com.cdmservicios.mantenimiento.models.Lubricante;
import com.cdmservicios.mantenimiento.repositories.LubricanteRepository;
import com.cdmservicios.mantenimiento.services.apis.LubricanteServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericServiceImplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LubricanteServiceImpl extends GenericServiceImplement<Lubricante, String> implements LubricanteServiceAPI {

    private final LubricanteRepository repository;

    public LubricanteServiceImpl(LubricanteRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Lubricante, String> getRepository() {
        return repository;
    }

    @Override
    @Transactional
    public List<Lubricante> findByEquipo(String code) {
        return repository.findByEquipo(code);
    }
}
