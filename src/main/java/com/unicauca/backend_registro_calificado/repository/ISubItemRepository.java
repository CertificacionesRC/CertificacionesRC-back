package com.unicauca.backend_registro_calificado.repository;


import com.unicauca.backend_registro_calificado.model.SubItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubItemRepository extends JpaRepository<SubItem, String> {
    //@Query(value = "SELECT * FROM sub_item s WHERE s.subitemid = :subitemid",nativeQuery = true)
    SubItem findSubItemById(String subItemId);
}