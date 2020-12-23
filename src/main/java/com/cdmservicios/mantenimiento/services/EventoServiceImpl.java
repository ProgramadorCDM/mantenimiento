package com.cdmservicios.mantenimiento.services;

import com.cdmservicios.mantenimiento.models.Evento;
import com.cdmservicios.mantenimiento.repositories.EventosRepository;
import com.cdmservicios.mantenimiento.services.apis.EventoServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericServiceImplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EventoServiceImpl extends GenericServiceImplement<Evento, String> implements EventoServiceAPI {
    private final EventosRepository repository;

    public EventoServiceImpl(EventosRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Evento, String> getRepository() {
        return repository;
    }
}
