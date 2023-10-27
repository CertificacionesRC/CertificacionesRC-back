package com.unicauca.backend_registro_calificado.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
public class RegistroCalificadoDTO {

    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Date fecha_creacion;

    private String colaboradores;

    private String autor;

    private EstadoRegistroCal estado;

    private ProgramaAcademicoDTO programaAcademico;

    private AnexoDTO anexo;

    private ObservacionDTO observacion;

    @JsonIgnore
    private ItemDTO item;

}
