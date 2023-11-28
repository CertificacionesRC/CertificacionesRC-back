package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "configuraciones")
public class Configuraciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "configuraciones_id")
    private long id;

    @Column(columnDefinition = "LONGTEXT")
    private String nombreVariable;

    @Column(columnDefinition = "LONGTEXT")
    private String contenido;


}

