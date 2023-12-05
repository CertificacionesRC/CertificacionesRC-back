package com.unicauca.backend_registro_calificado.domain;

import com.unicauca.backend_registro_calificado.model.enums.EstadoItem;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class SubItemDTO {

    private Integer id;
    private String nombre;
    private String contenido;
    private String guia;
    private EstadoItem estado;
    private ArchivoDTO archivo;

}
