package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.domain.SubItemDTO;

import java.util.List;

public interface IitemService {

    public Response<List<ItemDTO>> findAllItemsByIdRegistroCalificado(String idRegCalificado);

    //Response<List<ItemDTO>> findAllItemsByIdRegistroCalificado(String idRegCalificado);

    //public Response<ItemDTO> findItemById(String IdItem);
    public ItemDTO findItemById(Integer  IdItem);

    public Response<ItemDTO> updateItem(Integer  id,ItemDTO itemDTO);

}
