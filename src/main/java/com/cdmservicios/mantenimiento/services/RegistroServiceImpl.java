package com.cdmservicios.mantenimiento.services;

import com.cdmservicios.mantenimiento.models.Registro;
import com.cdmservicios.mantenimiento.repositories.RegistroRepository;
import com.cdmservicios.mantenimiento.services.apis.RegistroServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericServiceImplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistroServiceImpl extends GenericServiceImplement<Registro, String> implements RegistroServiceAPI {
    private final RegistroRepository repository;

    public RegistroServiceImpl(RegistroRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Registro, String> getRepository() {
        return repository;
    }

    @Override
    @Transactional
    public List<Registro> findAllByEquipo(String code) {
        return repository.findAllByEquipo(code);
    }
}
