package com.unicauca.backend_registro_calificado.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(length = 45)
    private String usuarioId;
    @Column(name = "u_nombre", length = 80)
    private String Nombre;

    @Column(length = 80)
    private String u_Correo;

    @Column( length = 80)
    private String u_Contrasena;
}
