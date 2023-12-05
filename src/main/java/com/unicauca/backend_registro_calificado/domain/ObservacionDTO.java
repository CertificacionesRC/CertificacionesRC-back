package com.unicauca.backend_registro_calificado.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObservacionDTO {

    private long id;
    private String contenido;
    private RegistroCalificadoDTO registroCalificado;

}
