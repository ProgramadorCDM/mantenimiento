package com.cdmservicios.mantenimiento.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipos")
@Data
public class Equipo {

    @Id
    private String code;

    @Column
    private String nombre;

    @Column
    private String section;

    @Column
    private String marca;

    @Column
    private String modelo;

    @Column
    private String tipo;

    @Column
    private String numeroSerie;

    @Column
    private String dimensiones;

    @Column
    private String peso;

    @Column
    private String capacidadTrabajo;

    @Column
    private String voltaje;

    @Column
    private String amperaje;

    @Column
    private String ciclos;

    @Column
    private String kw;

}
