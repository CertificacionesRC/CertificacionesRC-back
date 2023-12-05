package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.Response;
import  com.unicauca.backend_registro_calificado.domain.SubItemDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IsubItemService {
    public Response<SubItemDTO> findSubItemById(Integer IdSubItem);
    public Response<SubItemDTO> updateSubItem(Integer id, SubItemDTO subItemDTO);
    public Response<SubItemDTO> createSubItem(SubItemDTO subItemDTO);
    List<SubItemDTO> findAllSubItem();
    public List<SubItemDTO> findSubItemsByItem_IdAndParent_IdIsNull(Integer idItem);
    public Response<List<SubItemDTO>> findAllByParentId(Integer parentId);
    public ResponseEntity<?> updateStateSubItem(Integer idSubitem);

}
