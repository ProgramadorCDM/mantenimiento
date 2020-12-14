package com.cdmservicios.mantenimiento.services;

import com.cdmservicios.mantenimiento.models.Equipo;
import com.cdmservicios.mantenimiento.repositories.EquipoRepository;
import com.cdmservicios.mantenimiento.services.apis.EquipoServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericServiceImplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipoServiceImpl extends GenericServiceImplement<Equipo,String> implements EquipoServiceAPI {

    private final EquipoRepository repository;

    public EquipoServiceImpl(EquipoRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Equipo, String> getRepository() {
        return repository;
    }
}
