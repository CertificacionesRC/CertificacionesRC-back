package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "observacion")
public class Observacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String observacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registroCalificado_id")
    private RegistroCalificado registroCalificado;

}
