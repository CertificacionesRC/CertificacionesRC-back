package com.unicauca.backend_registro_calificado.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicauca.backend_registro_calificado.model.enums.EstadoItem;
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

   @Column(columnDefinition = "LONGTEXT")
   private String contenido;

   @Column(columnDefinition = "LONGTEXT")
   private String guia;

   private String nombre;
   @Enumerated(EnumType.STRING)
   private EstadoItem estado;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "registroCalificado_id")
   private RegistroCalificado registroCalificado;

//   @JsonIgnore()
   @OneToMany(mappedBy = "item")
   private List<SubItem> subItems;

   @OneToMany(mappedBy = "item")
   private List<ObservacionItem> observaciones;
}
