package com.unicauca.backend_registro_calificado.repository;

import com.unicauca.backend_registro_calificado.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IitemRepository extends JpaRepository<Item, String> {
    //@Query(value = "SELECT * FROM item WHERE item.itemid = :itemid", nativeQuery = true)
    //item findItemById(String itemid);
}
