package com.unicauca.backend_registro_calificado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {
    private long usuario_id;
    private String nombre;
    private String usuario;
    private String correo;
    private String contrasena;
    private Boolean estado;

    //pendiente roles
    @JsonIgnore
    private RoleDTO roles_id;
}
