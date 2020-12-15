package com.cdmservicios.mantenimiento.controllers;

import com.cdmservicios.mantenimiento.models.Registro;
import com.cdmservicios.mantenimiento.services.apis.RegistroServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/registros")
@Api(tags = "Registros")
public class RegistroRestController extends GenericRestController<Registro, String> {
    private final RegistroServiceAPI serviceAPI;

    public RegistroRestController(RegistroServiceAPI serviceAPI){
        super(serviceAPI);
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("/equipo/{code}")
    @ApiOperation(value = "Obtener Registros por Equipo", notes = "servicio para obtener los registros de cada equipo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registros encontrados correctamente"),
            @ApiResponse(code = 202, message = "Registros no encontrados se retorna vacía"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Entidad no encontrada")})
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> findAllByEquipo(@PathVariable String code) {
        return ResponseEntity.ok(serviceAPI.findAllByEquipo(code));
    }
}
