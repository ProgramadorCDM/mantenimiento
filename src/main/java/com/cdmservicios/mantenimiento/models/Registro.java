package com.cdmservicios.mantenimiento.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registros")
@Data
public class Registro {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idRegistro;

    @Column
    private LocalDate fecha;

    @OneToOne
    private Actividad actividad;

    @Column
    private String description;

    @Column
    private String vb;

    @OneToOne
    private Equipo equipo;
}
