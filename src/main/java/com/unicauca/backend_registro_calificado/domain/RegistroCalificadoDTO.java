package com.unicauca.backend_registro_calificado.domain;

import com.unicauca.backend_registro_calificado.model.enums.EstadosRegistroCal;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RegistroCalificadoDTO {

    private String registroCalificadoId;

    private Date registroCalificadoFecha;

    private ProgramaAcademicoDTO programaAcademicoIdDTO;

    private String registroCalDTOColabo;

    private String registroCalificadoAutor;

    private EstadosRegistroCal registroCalificadoEstado;

}
