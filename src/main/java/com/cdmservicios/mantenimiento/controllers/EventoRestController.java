package com.cdmservicios.mantenimiento.controllers;

import com.cdmservicios.mantenimiento.models.Evento;
import com.cdmservicios.mantenimiento.services.apis.EventoServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericRestController;
import com.cdmservicios.mantenimiento.shared.GenericServiceAPI;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/eventos")
@Api(tags = "eventos")
public class EventoRestController extends GenericRestController<Evento, String> {

    public EventoRestController(EventoServiceAPI serviceAPI) {
        super(serviceAPI);
    }
}
