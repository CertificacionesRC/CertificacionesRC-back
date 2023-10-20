package com.unicauca.backend_registro_calificado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubItemDTO {

    private Integer subitem_id;

    private String nombre;

    @JsonIgnore
    private SubItemDTO subitem_parent_id;

    @JsonIgnore
    private ItemDTO item_id;

    private String contenido;

    private String ayuda;

    @JsonIgnore
    private ArchivoDTO archivo;

}
