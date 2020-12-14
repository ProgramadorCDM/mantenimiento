package com.cdmservicios.mantenimiento.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "motores")
@Data
public class Motor {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idMotor;

    @Column
    private String marca;

    @Column
    private String modelo;

    @Column
    private String tipo;

    @Column
    private String clase;

    @Column
    private String fase;

    @Column
    private String noSerie;

    @Column
    private String aislamiento;

    @Column
    private String connection;

    @Column
    private String fs;

    @Column
    private String hp;

    @Column
    private String rpm;

    @Column
    private String voltaje;

    @Column
    private String amperaje;

    @Column
    private String frecuencia;

    @Column
    private String cos;

    @Column
    private String kw;

    @OneToOne(fetch = FetchType.LAZY)
    private Equipo equipo;
}
