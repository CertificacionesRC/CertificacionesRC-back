package com.unicauca.backend_registro_calificado.repository;

import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRegistroCalifRepository extends JpaRepository<RegistroCalificado, String> {

}
