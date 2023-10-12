package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.model.Item;
import com.unicauca.backend_registro_calificado.repository.IitemRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements IitemService{

    private final IitemRepository iitemRepository;

    private final ModelMapper modelMapper;

    public ItemServiceImpl(ModelMapper modelMapper, IitemRepository iitemRepository) {
        this.iitemRepository = iitemRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Response<List<ItemDTO>> findAllItemsByIdRegistroCalificado(String idRegCalificado) {
        return null;
    }

    @Override
    //public Response<ItemDTO> findItemById(String IdItem){
    public ItemDTO findItemById(String IdItem){
        System.out.println("IdItem: " + IdItem);
        Optional<Item> item = this.iitemRepository.findById(IdItem);
        System.out.println("IdItem itemid: " + item.get().getId());
        return modelMapper.map(this.iitemRepository.findById(IdItem), ItemDTO.class);
        //return null;
    };


}
