package com.unicauca.backend_registro_calificado.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class RolDTO {

    private Long rolId;
    private String rolNombre;
    private List<UsuarioDTO> lstUsuarios;

}
