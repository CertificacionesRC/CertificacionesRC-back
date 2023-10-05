package com.unicauca.backend_registro_calificado.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProgramaAcademicoDTO {

    private String pa_id;
    private String pa_nombre;
    private String pa_tipo;

}
