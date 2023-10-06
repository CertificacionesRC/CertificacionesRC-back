package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "anexos")
public class Anexos {
    @Id
    private String anexosId;

    private String anexosContenido;

    //Muchos anexos pueden estar en un registro calificado
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rc_id")
    private RegistroCalificado registroCaliId;



}
