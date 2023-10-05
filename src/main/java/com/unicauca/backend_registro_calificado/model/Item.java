package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "item")
public class Item {
   @Id
   private String i_id;

   private String i_nombre;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "rc_id")
   private RegistroCalificado rc_id;



}
