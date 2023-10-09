package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subItem")
public class SubItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;

    private String contenido;

    private String guia;

    //De cada subitem se debe saber a que subitem pertenece
    //Muchos subitems pueden estar en un subitem
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private SubItem parentSubItem;

    //Un subitem tiene varios subitems
    @OneToMany(mappedBy = "parentSubItem", fetch = FetchType.LAZY)
    private List<SubItem> subItems;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToMany(mappedBy = "subItem", fetch = FetchType.LAZY)
    private List<Archivo> archivos;



}
