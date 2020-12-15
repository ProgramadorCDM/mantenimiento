package com.cdmservicios.mantenimiento.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instrucciones")
@Data
public class Instruction {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idInstruction;

    @OneToOne
    @NotNull
    private Equipo equipo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinTable(name = "instrucciones_actividades",
            joinColumns = @JoinColumn(name = "instrucciones_id_instruction"),
            inverseJoinColumns = @JoinColumn(name = "actividades_id_actividad")
    )
    private final List<Actividad> actividades;

    @Column
    private String observaciones;

    public Instruction() {
        this.actividades = new ArrayList<>();
    }

    public void addActividad(Actividad actividad){
        this.actividades.add(actividad);
    }

    public void removeActividad(Actividad actividad){
        this.actividades.remove(actividad);
    }
}
