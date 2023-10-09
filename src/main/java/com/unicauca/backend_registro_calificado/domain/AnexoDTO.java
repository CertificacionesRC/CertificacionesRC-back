package com.unicauca.backend_registro_calificado.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnexoDTO {
    private Integer id;

    private String contenido;

    private RegistroCalificadoDTO registroCalificado;



}

