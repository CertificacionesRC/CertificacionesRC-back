package com.unicauca.backend_registro_calificado.domain;

import com.unicauca.backend_registro_calificado.model.enums.TipoPrograma;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProgramaAcademicoDTO {

    private Integer id;
    private String nombre;
    private TipoPrograma tipo;
    private String facultad;
    private RegistroCalificadoDTO registroCalificado;

}
