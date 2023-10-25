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
@Table(name = "registroCalificado")
public class RegistroCalificado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_calificado_id")
    private long id;
    private Date fechaCreacion;
    private String colaboradores;
    private String autor;

    @Enumerated(EnumType.STRING) // Mapea el enum como una cadena en la base de datos
    private EstadoRegistroCal estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "programa_id")
    private ProgramaAcademico programaAcademico;

    @OneToMany(mappedBy = "registroCalificado")
    private List<Anexo> Anexos;

    @OneToMany(mappedBy = "registroCalificado")
    private List<Observacion> observaciones;

    @OneToMany(mappedBy = "registroCalificado")
    private List<Item> items;






}
