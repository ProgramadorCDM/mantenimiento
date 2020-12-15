package com.cdmservicios.mantenimiento.controllers;

import com.cdmservicios.mantenimiento.models.Actividad;
import com.cdmservicios.mantenimiento.models.Instruction;
import com.cdmservicios.mantenimiento.services.apis.InstructionServiceAPI;
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
@RequestMapping("/api/instrucciones")
@Api(tags = "Instrucciones")
public class InstructionRestController extends GenericRestController<Instruction, String> {
    private final InstructionServiceAPI serviceAPI;

    public InstructionRestController(InstructionServiceAPI serviceAPI){
        super(serviceAPI);
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("/equipo/{code}")
    @ApiOperation(value = "Obtener Instrucciones por Equipos", notes = "servicio para obtener las instrucciones de cada equipo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instrucción encontrada correctamente"),
            @ApiResponse(code = 202, message = "Instrucción no encontrada se retorna vacía"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Entidad no encontrada")})
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> findByEquipo(@PathVariable String code) {
        Instruction entity = this.serviceAPI.findByEquipo(code);
        if (entity == null) return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PutMapping("/{id}/asignar-actividades")
    @ApiOperation(value = "Asignar actividades por ", notes = "servicio para agregar actividades al instructivo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Actividad agregada correctamente"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Entidad no encontrada")})
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> asignarActividades(@RequestBody Actividad actividad, @PathVariable String id){
        Instruction entity = serviceAPI.getById(id);
        if (entity == null) return ResponseEntity.notFound().build();
        entity.addActividad(actividad);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceAPI.save(entity));
    }

    @PutMapping("/{id}/eliminar-actividades")
    @ApiOperation(value = "Eliminar actividades por ", notes = "servicio para eliminar actividades al instructivo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Actividad eliminada correctamente"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Entidad no encontrada")})
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> eliminarActividades(@RequestBody Actividad actividad, @PathVariable String id){
        Instruction entity = serviceAPI.getById(id);
        if (entity == null) return ResponseEntity.notFound().build();
        entity.removeActividad(actividad);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceAPI.save(entity));
    }
}
