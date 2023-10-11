package com.unicauca.backend_registro_calificado.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDTO {

    private Integer id;

    private String nombre;

    private String guia;

    private RegistroCalificadoDTO registroCalificado;

    private SubItemDTO subitem;

}
