package com.unicauca.backend_registro_calificado.repository;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IitemRepository extends JpaRepository<Item, String> {
    @Query(value = "SELECT * FROM item WHERE item.item_id = :id", nativeQuery = true)
    Item findItemById(Integer id);

    Optional<Item> findById(Integer idItem);

}
