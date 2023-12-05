package com.unicauca.backend_registro_calificado.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    private long id;
    private String nombre;
    private String correo;
    private String contrasena;
    private Boolean estado;
    private RolDTO rol;

}
