package com.cdmservicios.mantenimiento.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "lubricantes")
@Data
public class Lubricante {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idLubricante;

    @Column
    @NotBlank
    private String parte;

    @Column
    private String method;

    @Column
    @NotBlank
    private String lubricante;

    @Column
    private String frecuencia;

    @Column
    private String notas;

    @OneToOne
    private Equipo equipo;
}
