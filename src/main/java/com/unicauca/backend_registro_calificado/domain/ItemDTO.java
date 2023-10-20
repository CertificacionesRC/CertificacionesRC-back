package com.unicauca.backend_registro_calificado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDTO {

    private Integer item_id;

    private String nombre;

    @JsonIgnore
    private RegistroCalificadoDTO registro_calificado_id;

    private  String contenido;

    private String ayuda;



}
