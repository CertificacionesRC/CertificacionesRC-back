package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import org.springframework.stereotype.Service;
import com.unicauca.backend_registro_calificado.services.IitemService;

import java.util.List;

@Service
public class ItemServiceImpl implements IitemService{
    @Override
    public Response<List<ItemDTO>> findAllItemsByIdRegistroCalificado(String idRegCalificado) {
        return null;
    }
}
