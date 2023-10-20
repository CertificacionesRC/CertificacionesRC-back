package com.unicauca.backend_registro_calificado.model;

import com.unicauca.backend_registro_calificado.domain.SubItemDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "archivo")
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archivo_id")
    private long archivoId;

    private String contenido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subitem_id")
    private SubItem subitem_id;
}
