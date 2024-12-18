package com.unicauca.backend_registro_calificado.model;

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
@Table(name = "programaAcademico")

public class ProgramaAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programa_academico_id")
    private long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private TipoPrograma tipo;
    private String facultad;
    @OneToMany(mappedBy = "programaAcademico")
    private List<RegistroCalificado> registroCalificados;

}
