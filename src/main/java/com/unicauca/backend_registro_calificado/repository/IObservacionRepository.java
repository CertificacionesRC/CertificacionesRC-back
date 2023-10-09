package com.unicauca.backend_registro_calificado.repository;

import com.unicauca.backend_registro_calificado.model.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IObservacionRepository extends JpaRepository<Observacion,String> {

}
