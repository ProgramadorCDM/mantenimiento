package com.cdmservicios.mantenimiento.controllers;

import com.cdmservicios.mantenimiento.models.Actividad;
import com.cdmservicios.mantenimiento.services.apis.ActividadServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericRestController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/actividades")
@Api(tags = "Actividades")
public class ActividadRestController extends GenericRestController<Actividad, String> {
    public ActividadRestController(ActividadServiceAPI serviceAPI) {
        super(serviceAPI);
    }
}
