package com.cdmservicios.mantenimiento.controllers;

import com.cdmservicios.mantenimiento.models.Motor;
import com.cdmservicios.mantenimiento.services.apis.MotorServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Obtener Motores por Equipos", notes = "servicio para obtener los motores de cada equipo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motor encontrado correctamente"),
            @ApiResponse(code = 202, message = "Motor no encontrado se retorna vacio"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Entidad no encontrada")})
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> findByEquipo(@PathVariable String code) {
        Motor entity = this.serviceAPI.findMotorByEquipo(code);
        if (entity == null) return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
