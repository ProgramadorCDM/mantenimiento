package com.cdmservicios.mantenimiento.services;

import com.cdmservicios.mantenimiento.models.Actividad;
import com.cdmservicios.mantenimiento.repositories.ActividadRepository;
import com.cdmservicios.mantenimiento.services.apis.ActividadServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericServiceImplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ActividadServiceImpl extends GenericServiceImplement<Actividad, String> implements ActividadServiceAPI {
    private final ActividadRepository repository;

    public ActividadServiceImpl(ActividadRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Actividad, String> getRepository() {
        return repository;
    }
}
