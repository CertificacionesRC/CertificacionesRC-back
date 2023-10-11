package com.unicauca.backend_registro_calificado.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubItemDTO {

    private Integer id;

    private String nombre;

    private String contenido;

    private String guia;

    private ItemDTO item;

    private SubItemDTO subitem;

    private ArchivoDTO archivo;




}
