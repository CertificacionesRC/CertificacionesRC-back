package com.unicauca.backend_registro_calificado.repository;

import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IRegistroCalifRepository extends JpaRepository<RegistroCalificado, String> {

    //@Query(value = "SELECT * FROM registro_calificado WHERE registro_calificado.estado=?1", nativeQuery = true)
    List<RegistroCalificado> findByEstado(EstadoRegistroCal estado);




}
