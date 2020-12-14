package com.cdmservicios.mantenimiento.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "motores")
@Data
public class Motor {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idMotor;

    @Column
    @NotBlank
    private String marca;

    @Column
    @NotBlank
    private String modelo;

    @Column
    private String tipo;

    @Column
    private String clase;

    @Column
    @NotBlank
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
    @NotBlank
    private String hp;

    @Column
    @NotBlank
    private String rpm;

    @Column
    @NotBlank
    private String voltaje;

    @Column
    @NotBlank
    private String amperaje;

    @Column
    @NotBlank
    private String frecuencia;

    @Column
    private String cos;

    @Column
    private String kw;

    @NotNull
    @OneToOne
    private Equipo equipo;
}
