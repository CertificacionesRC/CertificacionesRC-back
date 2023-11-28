package com.unicauca.backend_registro_calificado.repository;

import com.unicauca.backend_registro_calificado.model.ProgramaAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  IProgramaAcademicoRepository extends JpaRepository<ProgramaAcademico, String> {
    public ProgramaAcademico findById(Long programa_academico_id);

}
