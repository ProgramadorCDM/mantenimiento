package com.cdmservicios.mantenimiento.controllers;

import com.cdmservicios.mantenimiento.models.Motor;
import com.cdmservicios.mantenimiento.services.apis.MotorServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericRestController;
import com.cdmservicios.mantenimiento.shared.GenericServiceAPI;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/motores")
public class MotorRestController extends GenericRestController<Motor, String> {


    public MotorRestController(MotorServiceAPI serviceAPI) {
        super(serviceAPI);
    }
}
