package com.unicauca.backend_registro_calificado.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
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
   private long itemId;

   private String contenido;

   private String ayuda;

   private String nombre;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "registro_calificado_id")
   private RegistroCalificado registro_calificado_id;

   @OneToMany(mappedBy = "item")
   private List<SubItem> subItems;

}
