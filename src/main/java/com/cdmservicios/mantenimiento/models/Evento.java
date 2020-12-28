package com.cdmservicios.mantenimiento.models;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name= "eventos")
@Data
public class Evento {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idEvento;

    @Column
    @NotBlank
    private  String title;

    @Column
    @NotNull
    private LocalDate start;

    @Column
    private  String url;

    @OneToOne
    private Actividad actividad;

    @ColumnDefault("false")
    private Boolean cumplido;
}
