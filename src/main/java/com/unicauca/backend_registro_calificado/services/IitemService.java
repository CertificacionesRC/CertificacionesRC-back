package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IitemService {
    public Response<ItemDTO> findItemById(Integer  IdItem);
    List<ItemDTO> findAllItem();
    public Response<ItemDTO> updateItem(Integer  id,ItemDTO itemDTO);
    public Response<ItemDTO> createItem(ItemDTO itemDTO);
    public ResponseEntity<?> updateStateItem(Integer id);

}
