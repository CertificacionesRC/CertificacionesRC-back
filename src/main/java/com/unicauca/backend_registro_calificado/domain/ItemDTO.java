package com.unicauca.backend_registro_calificado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicauca.backend_registro_calificado.model.SubItem;
import com.unicauca.backend_registro_calificado.model.enums.EstadoItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ItemDTO {

    private Integer id;

    private  String contenido;

    private String guia;

    private String nombre;

    private EstadoItem estado;

//    private RegistroCalificadoDTO registroCalificado;
//    @JsonIgnore()
    private List<SubItem> subItems;

    public String getNombre() {
        return nombre;
    }
}
