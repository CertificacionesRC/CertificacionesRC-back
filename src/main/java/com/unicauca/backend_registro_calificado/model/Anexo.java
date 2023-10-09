package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "anexo")
public class Anexo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String contenido;

    //Muchos anexos pueden estar en un registro calificado
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registroCalificado_id")
    private RegistroCalificado registroCalificado;



}
