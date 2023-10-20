package com.unicauca.backend_registro_calificado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RegistroCalificadoDTO {

    private Integer registro_calificado_id;

    private Date fecha_creacion;

    private ProgramaAcademicoDTO programa_academico_id;

    private String colaboradores;

    private String autor;

    private EstadoRegistroCal estado;

    //private AnexoDTO anexo;

    //private ObservacionDTO observacion;



    @JsonIgnore
    private ItemDTO item;

}
