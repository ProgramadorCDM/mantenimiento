package com.cdmservicios.mantenimiento.controllers;

import com.cdmservicios.mantenimiento.models.Equipo;
import com.cdmservicios.mantenimiento.services.apis.EquipoServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericRestController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/equipos")
@Api(tags = "equipo")
public class EquipoRestController extends GenericRestController<Equipo, String> {


    public EquipoRestController(EquipoServiceAPI serviceAPI) {
        super(serviceAPI);
    }


}
