package com.unicauca.backend_registro_calificado.repository;

import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRegistroCalifRepository extends JpaRepository<RegistroCalificado, String> {

    List<RegistroCalificado> findByEstado(EstadoRegistroCal estado);
    List<RegistroCalificado> findAllByProgramaAcademicoId(Long programaId);
    RegistroCalificado findByAutor(String autor);

}
