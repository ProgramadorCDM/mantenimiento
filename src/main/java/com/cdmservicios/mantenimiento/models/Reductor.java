package com.cdmservicios.mantenimiento.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "reductores")
@Data
public class Reductor {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idReductor;

    @Column
    private String marca;

    @Column
    private String modelo;

    @Column
    @NotBlank
    private String tipo;

    @Column
    private String noSerie;

    @Column
    private String rpm;

    @Column
    private String hp;

    @Column
    private String relation;

    @Column
    private String rpmSalida;

    @Column
    private String otros;

    @OneToOne
    private Equipo equipo;


}
