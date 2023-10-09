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
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   private String nombre;

   private String contenido;

   private String guia;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "registroCalificado_id")
   private RegistroCalificado registroCalificado;

   @OneToMany(mappedBy = "item")
   private List<SubItem> subItems;



}
