package com.unicauca.backend_registro_calificado.model;

import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.model.enums.TipoPrograma;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "programa_academico")

public class ProgramaAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programa_academico_id")
    private long programaAcademicoId;

    private String nombre;

    private String facultad;

    @Enumerated(EnumType.STRING)
    private TipoPrograma tipo;

    @OneToMany(mappedBy = "programaAcademico")
    private List<RegistroCalificado> registroCalificado;

}
