package com.cdmservicios.mantenimiento.controllers;

import com.cdmservicios.mantenimiento.models.Lubricante;
import com.cdmservicios.mantenimiento.services.apis.LubricanteServiceAPI;
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
@RequestMapping("/api/lubricantes")
@Api(tags = "lubricantes")
public class LubricanteRestController extends GenericRestController<Lubricante, String> {
    private final LubricanteServiceAPI serviceAPI;

    public LubricanteRestController(LubricanteServiceAPI serviceAPI) {
        super(serviceAPI);
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("/equipo/{code}")
    @ApiOperation(value = "Obtener Lubricantes por Equipos", notes = "servicio para obtener los lubricantes de cada equipo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lubricante encontrado correctamente"),
            @ApiResponse(code = 202, message = "Lubricante no encontrado se retorna vacío"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Entidad no encontrada")})
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> findByEquipo(@PathVariable String code) {
        return new ResponseEntity<>(this.serviceAPI.findByEquipo(code), HttpStatus.OK);
    }

}
