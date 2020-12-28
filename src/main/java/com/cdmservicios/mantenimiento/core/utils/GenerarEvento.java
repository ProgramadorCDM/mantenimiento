package com.cdmservicios.mantenimiento.core.utils;

import com.cdmservicios.mantenimiento.models.Actividad;
import com.cdmservicios.mantenimiento.models.Evento;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class GenerarEvento {

    List<Evento> eventos;

    public GenerarEvento() {
        this.eventos = new ArrayList<>();
    }

    public List<Evento> GeneradorEventos(Actividad actividad, LocalDate fecha, String title) {


        int year = fecha.getYear();

        do {
            Evento evento = new Evento();
            evento.setTitle(actividad.getTipoActividad()+" de "+ title);
            evento.setStart(fecha);
            evento.setUrl("");
            evento.setActividad(actividad);
            eventos.add(evento);
            fecha = fecha.plusDays(actividad.getFrecuenciaActividad());
        } while (fecha.getYear() == year);

        return eventos;
    }
}
