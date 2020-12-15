package com.cdmservicios.mantenimiento.controllers;

import com.cdmservicios.mantenimiento.models.Reductor;
import com.cdmservicios.mantenimiento.services.apis.ReductorServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericRestController;
import com.cdmservicios.mantenimiento.shared.GenericServiceAPI;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/reductores")
@Api(tags = "reductores")
public class ReductorRestController extends GenericRestController<Reductor, String> {


    private final ReductorServiceAPI serviceAPI;

    public ReductorRestController(ReductorServiceAPI serviceAPI) {
        super(serviceAPI);
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("/equipo/{code}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> findByEquipo(@PathVariable String code) {
        Reductor reductor = this.serviceAPI.findByEquipo(code);
        if(reductor == null) return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        return new ResponseEntity<>(reductor, HttpStatus.OK);
    }
}
