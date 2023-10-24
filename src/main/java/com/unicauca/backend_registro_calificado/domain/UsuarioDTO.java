package com.unicauca.backend_registro_calificado.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

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
