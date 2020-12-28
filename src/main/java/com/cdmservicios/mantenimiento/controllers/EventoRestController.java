package com.cdmservicios.mantenimiento.controllers;

import com.cdmservicios.mantenimiento.core.utils.GenerarEvento;
import com.cdmservicios.mantenimiento.models.Actividad;
import com.cdmservicios.mantenimiento.models.Evento;
import com.cdmservicios.mantenimiento.services.apis.EventoServiceAPI;
import com.cdmservicios.mantenimiento.shared.GenericRestController;
import com.cdmservicios.mantenimiento.shared.GenericServiceAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/eventos")
@Api(tags = "eventos")
public class EventoRestController extends GenericRestController<Evento, String> {

    private final EventoServiceAPI serviceAPI;

    public EventoRestController(EventoServiceAPI serviceAPI) {
        super(serviceAPI);
        this.serviceAPI = serviceAPI;
    }

    @PostMapping("/generar/{fecha}/{title}")
    @ApiOperation(value = "Eliminar actividades por ", notes = "servicio para eliminar actividades al instructivo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Actividad eliminada correctamente"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Entidad no encontrada")})
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> generarEventos(
            @RequestBody Actividad actividad,
            @PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate fecha,
            @PathVariable String title
    ){
        GenerarEvento generarEvento = new GenerarEvento();
        List<Evento> eventos = generarEvento.GeneradorEventos(actividad,fecha, title);
        List<Evento> eventoList = new ArrayList<>();
        for (Evento evento : eventos) {
            eventoList.add(serviceAPI.save(evento));
        }
        return ResponseEntity.ok(eventoList);
    }
}
