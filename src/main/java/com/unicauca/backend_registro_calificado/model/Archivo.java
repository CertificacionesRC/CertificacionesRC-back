package com.unicauca.backend_registro_calificado.model;

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
    private long id;
    private String contenido;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subItem_id")
    private SubItem subItem;

}
