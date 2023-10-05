package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "programaacademico")

public class ProgramaAcademico {

    @Id
    private String pa_id;
    private String pa_nombre;
    //private String facultad;

    private String pa_tipo;

}
