package com.unicauca.backend_registro_calificado.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private long usuarioId;

    private String nombre;

    private String usuario;

    private String correo;

    private String contrasena;

    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;
}
