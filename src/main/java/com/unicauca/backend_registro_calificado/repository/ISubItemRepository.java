package com.unicauca.backend_registro_calificado.repository;


import com.unicauca.backend_registro_calificado.model.SubItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubItemRepository extends JpaRepository<SubItem, String> {
    SubItem findSubItemById(Integer subItemId);
    List<SubItem> findSubItemsByItem_IdAndParentSubItem_Id(Integer idItem, Integer idParent);
}