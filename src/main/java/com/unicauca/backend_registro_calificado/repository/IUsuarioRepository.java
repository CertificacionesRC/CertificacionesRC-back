package com.unicauca.backend_registro_calificado.repository;


import com.unicauca.backend_registro_calificado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, String> {


}
