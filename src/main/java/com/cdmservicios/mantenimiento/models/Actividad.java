package com.cdmservicios.mantenimiento.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "actividades")
@Data
public class Actividad {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idActividad;

    @NotBlank
    @Column
    private String tipoActividad;

    @NotBlank
    @Column
    private String descriptionActividad;

    @NotBlank
    @Column
    private String frecuenciaActividad;
}
