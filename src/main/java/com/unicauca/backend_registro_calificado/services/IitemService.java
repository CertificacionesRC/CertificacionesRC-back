package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.domain.SubItemDTO;
import com.unicauca.backend_registro_calificado.model.enums.EstadoItem;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface IitemService {


    public Response<ItemDTO> findItemById(Integer  IdItem);

    List<ItemDTO> findAllItem();

    public Response<ItemDTO> updateItem(Integer  id,ItemDTO itemDTO);

    public Response<ItemDTO> createItem(ItemDTO itemDTO);

    public ResponseEntity<?> updateStateItem(Integer id);

}
