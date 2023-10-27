package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.domain.SubItemDTO;

import java.util.List;

public interface IitemService {


    public Response<ItemDTO> findItemById(Integer  IdItem);

    List<ItemDTO> findAllItem();

    public Response<ItemDTO> updateItem(Integer  id,ItemDTO itemDTO);

    public Response<ItemDTO> createItem(ItemDTO itemDTO);

}
