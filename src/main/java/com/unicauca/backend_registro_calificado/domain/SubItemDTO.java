package com.unicauca.backend_registro_calificado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicauca.backend_registro_calificado.model.SubItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SubItemDTO {

    private Integer id;
    private String nombre;
    private String contenido;
    private String guia;
//    private ItemDTO item;
//    private List<SubItemDTO> subItems;
    private ArchivoDTO archivo;

}
