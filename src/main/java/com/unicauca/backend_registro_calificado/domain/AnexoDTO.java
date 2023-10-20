package com.unicauca.backend_registro_calificado.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnexoDTO {

    private Integer anexo_id;

    private String contenido;

    private RegistroCalificadoDTO registro_calificado_id;



}

