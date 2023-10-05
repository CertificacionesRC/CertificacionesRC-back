package com.unicauca.backend_registro_calificado.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
public class UsuarioDTO {
    private String usuarioId;
    private String Nombre;
    private String u_Correo;
    private String u_Contrasena;
}
