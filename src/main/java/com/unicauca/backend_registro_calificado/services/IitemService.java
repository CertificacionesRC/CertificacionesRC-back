package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;

import java.util.List;

public interface IitemService {

    public Response<List<ItemDTO>> findAllItemsByIdRegistroCalificado(String idRegCalificado);

    //public Response<ItemDTO> findItemById(String IdItem);
    public ItemDTO findItemById(String IdItem);
}
