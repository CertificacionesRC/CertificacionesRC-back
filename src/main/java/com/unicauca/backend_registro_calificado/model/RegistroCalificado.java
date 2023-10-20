package com.unicauca.backend_registro_calificado.model;

import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "registro_calificado")
public class RegistroCalificado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_calificado_id")
    private long registroCalificadoId;
    private Date fechaCreacion;
    private String colaboradores;
    private String autor;

    @Enumerated(EnumType.STRING) // Mapea el enum como una cadena en la base de datos
    private EstadoRegistroCal estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "programa_academico_id")
    private ProgramaAcademico programaAcademico;

    @OneToMany(mappedBy = "registroCalificado")
    private List<Anexo> anexos;

    @OneToMany(mappedBy = "registroCalificado")
    private List<ObservacionRegistroCalificado> observaciones;

    @OneToMany(mappedBy = "registroCalificado")
    private List<Item> items;


}
