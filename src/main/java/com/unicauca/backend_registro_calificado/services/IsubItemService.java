package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;

import  com.unicauca.backend_registro_calificado.domain.SubItemDTO;

public interface IsubItemService {

    /**
     * Metodo que permite actualizar un registro en base de datos, sobre la tabla Environment,
     * actualizando la informacion del ambiente que se pasa por parametro.
     * @param subItemDTO objeto con la informacion a actualizar del subItem
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene la informacion sobre el resultado de la actualizacion
     */
    public Response<SubItemDTO> findSubItemById(Integer IdSubItem);
    public Response<SubItemDTO> updateSubItem(Integer id, SubItemDTO subItemDTO);
    public Response<SubItemDTO> createSubItem(SubItemDTO subItemDTO);
}