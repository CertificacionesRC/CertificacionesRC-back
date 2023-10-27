package com.unicauca.backend_registro_calificado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDTO {

    private Integer id;

    private  String contenido;

    private String guia;

    private String nombre;

    private RegistroCalificadoDTO registroCalificado;

}
