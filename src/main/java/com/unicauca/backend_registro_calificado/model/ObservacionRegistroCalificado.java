package com.unicauca.backend_registro_calificado.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "observacion_registro_calificado")
public class ObservacionRegistroCalificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "observacion_registro_calificado_id")
    private long observacionRegistroCalificadoId;

    private String observacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registro_calificado_id")
    private RegistroCalificado registro_calificado_id;

}
