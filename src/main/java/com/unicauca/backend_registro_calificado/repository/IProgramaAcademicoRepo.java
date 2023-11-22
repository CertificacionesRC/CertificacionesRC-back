package com.unicauca.backend_registro_calificado.repository;

import com.unicauca.backend_registro_calificado.model.ProgramaAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProgramaAcademicoRepo extends JpaRepository<ProgramaAcademico, String> {

        @Query(value = "SELECT * FROM programa_academico WHERE programa_academico_id = :programa_academico_id", nativeQuery = true)
        List<ProgramaAcademico> findByRegistroId(Long programa_academico_id);

        }

