package com.unicauca.backend_registro_calificado.repository;

import com.unicauca.backend_registro_calificado.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IitemRepository extends JpaRepository<Item, String> {

}
