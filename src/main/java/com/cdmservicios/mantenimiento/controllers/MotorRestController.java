package com.cdmservicios.mantenimiento.controllers;

import com.cdmservicios.mantenimiento.models.Motor;
import com.cdmservicios.mantenimiento.services.apis.MotorServiceAPI;
import com.cdmservicios.mantenimiento.services.apis.ReductorServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericRestController;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/motores")
@Api(tags = "motor")
public class MotorRestController extends GenericRestController<Motor, String> {

    private final MotorServiceAPI serviceAPI;


    public MotorRestController(MotorServiceAPI serviceAPI) {
        super(serviceAPI);
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("/equipo/{code}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> findByEquipo(@PathVariable String code) {
        Motor entity = this.serviceAPI.findMotorByEquipo(code);
        if(entity == null) return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
