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
@Table(name = "item")
public class Item {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "item_id")
   private long id;

   private String contenido;

   private String guia;

   private String nombre;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "registroCalificado_id")
   private RegistroCalificado registroCalificado;

   @OneToMany(mappedBy = "item")
   private List<SubItem> subItems;

   @OneToMany(mappedBy = "item")
   private List<ObservacionItem> observaciones;
}
