package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rol")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Integer id;

    @Column(name = "nombre_rol", nullable = false, length = 15)
    private String nombre;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> LstUsuarioRol ;
}
