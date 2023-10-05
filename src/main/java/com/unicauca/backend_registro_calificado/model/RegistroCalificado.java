package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "registrocalificado")
public class RegistroCalificado {
    @Id
    private String rc_id;

    private Date rc_fecha_creacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pa_id")
    private ProgramaAcademico pa_id;

    private String rc_colaboradores;

    private String rc_autor;

    private String rc_estado;






}
