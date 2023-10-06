package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subitem")
public class Subitem {
    @Id
    private String subitemId;

    private String subitemNombre;

    //De cada subitem se debe saber a que subitem pertenece
    //Muchos subitems pueden estar en un subitem
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Subitem parentSubitem;

    //Un subitem tiene varios subitems
    @OneToMany(mappedBy = "parentEnvironment", fetch = FetchType.LAZY)
    private Set<Subitem> subEnvironments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "i_id")
    private Item itemId;

    private String subitemContenido;

    private String subitemGuia;

}
