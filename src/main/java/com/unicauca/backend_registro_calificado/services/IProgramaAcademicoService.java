package com.unicauca.backend_registro_calificado.services;
import com.unicauca.backend_registro_calificado.domain.ProgramaAcademicoDTO;
import java.util.List;

public interface IProgramaAcademicoService {
    public List<ProgramaAcademicoDTO> findAll();

}
