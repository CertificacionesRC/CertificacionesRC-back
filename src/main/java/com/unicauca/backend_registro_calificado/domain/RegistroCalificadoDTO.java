package com.unicauca.backend_registro_calificado.domain;

import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RegistroCalificadoDTO {

    private Integer id;

    private Date fecha;

    private String colaboradores;

    private String autor;

    private EstadoRegistroCal estado;

    private ProgramaAcademicoDTO programaAcademico;

    private AnexoDTO anexo;

    private ObservacionDTO observacion;

    private ItemDTO item;

}
