package com.unicauca.backend_registro_calificado.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubitemDTO {

    private String subitemId;

    private String subitemNombre;

    private ItemDTO itemIdDTO;

    private String subitemContenido;

    private String subitemGuia;
}
