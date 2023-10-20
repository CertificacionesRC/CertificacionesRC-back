package com.unicauca.backend_registro_calificado.model;

import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anexo_id")
    private long anexoId;

    private String contenido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registro_calificado_id")
    private RegistroCalificado registroCalificado;

}
