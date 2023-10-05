package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.Text;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "anexos")
public class Anexos {
    @Id
    private String an_id;

    private String an_contenido;

    //Muchos anexos pueden estar en un registro calificado
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rc_id")
    private RegistroCalificado rc_id;



}
