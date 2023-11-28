package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ProgramaAcademicoDTO;
import com.unicauca.backend_registro_calificado.model.ProgramaAcademico;

import java.util.List;

public interface IProgramaAcademicoService {

    //id y nombre de programa academico
    public List<ProgramaAcademicoDTO> findAll();

}
