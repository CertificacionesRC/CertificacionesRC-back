package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;

import  com.unicauca.backend_registro_calificado.domain.SubItemDTO;

import java.util.List;

public interface IsubItemService {

    public Response<SubItemDTO> findSubItemById(Integer IdSubItem);
    public Response<SubItemDTO> updateSubItem(Integer id, SubItemDTO subItemDTO);
    public Response<SubItemDTO> createSubItem(SubItemDTO subItemDTO);

    List<SubItemDTO> findAllSubItem();

    List<SubItemDTO> findSubItemsByItem_IdAndParent_IdIsNull(Integer idItem);
}