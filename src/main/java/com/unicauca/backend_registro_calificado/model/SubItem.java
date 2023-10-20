package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subitem")
public class SubItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subitem_id")
    private long id;
    @Column(name = "contenido", nullable = true)
    private String contenido;
    @Column(name = "guia", nullable = false)
    private String guia;
    @Column(name = "nombre", nullable = false)
    private String nombre;

    //De cada subitem se debe saber a que subitem pertenece
    //Muchos subitems pueden estar en un subitem
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private SubItem parentSubItem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;

    //Un subitem tiene varios subitems
    @OneToMany(mappedBy = "parentSubItem", fetch = FetchType.LAZY) //cuando se usa el LAZY en el mapeo no se debe llamar a la propiedad porque se vuelve ineficiente
    private List<SubItem> subItems;

    @OneToMany(mappedBy = "subItem", fetch = FetchType.LAZY)
    private List<Archivo> archivos;


}
