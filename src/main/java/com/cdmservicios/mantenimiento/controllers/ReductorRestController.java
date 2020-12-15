package com.cdmservicios.mantenimiento.controllers;

import com.cdmservicios.mantenimiento.models.Reductor;
import com.cdmservicios.mantenimiento.services.apis.ReductorServiceAPI;
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
@RequestMapping("/api/reductores")
@Api(tags = "reductores")
public class ReductorRestController extends GenericRestController<Reductor, String> {


    private final ReductorServiceAPI serviceAPI;

    public ReductorRestController(ReductorServiceAPI serviceAPI) {
        super(serviceAPI);
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("/equipo/{code}")
    @ApiOperation(value = "Obtener Reductores por Equipos", notes = "servicio para obtener los reductores de cada equipo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reductor encontrado correctamente"),
            @ApiResponse(code = 202, message = "Reductor no encontrado se retorna vacío"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Entidad no encontrada")})
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> findByEquipo(@PathVariable String code) {
        Reductor reductor = this.serviceAPI.findByEquipo(code);
        if (reductor == null) return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        return new ResponseEntity<>(reductor, HttpStatus.OK);
    }
}
