package com.unicauca.backend_registro_calificado.repository;


import com.unicauca.backend_registro_calificado.model.SubItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubItemRepository extends JpaRepository<SubItem, String> {
    //@Query(value = "SELECT * FROM sub_item s WHERE s.id = :subItemId",nativeQuery = true)
    SubItem findSubItemById(Integer subItemId);

    @Query(value = "SELECT * FROM sub_item WHERE parent_id = :parent_id",nativeQuery = true)
    List<SubItem> findAllByParentId(@Param("parent_id") Integer parentId);
}