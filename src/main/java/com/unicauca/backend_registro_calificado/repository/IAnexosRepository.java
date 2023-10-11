package com.unicauca.backend_registro_calificado.repository;

import com.unicauca.backend_registro_calificado.model.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnexosRepository extends JpaRepository<Anexo, String> {
}
