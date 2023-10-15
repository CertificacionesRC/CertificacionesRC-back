package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.model.Item;
import com.unicauca.backend_registro_calificado.repository.IitemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements IitemService{

    private final IitemRepository iitemRepository;

    //private final ModelMapper modelMapper;

    public ItemServiceImpl(IitemRepository iitemRepository) {
        this.iitemRepository = iitemRepository;

    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Response<List<ItemDTO>> findAllItemsByIdRegistroCalificado(String idRegCalificado) {
        return null;
    }

    @Override
    //public Response<ItemDTO> findItemById(String IdItem){
    public ItemDTO findItemById(String IdItem){
        System.out.println("IdItem: " + IdItem);

        Optional<Item> itemoptional = this.iitemRepository.findById(IdItem);
        Item item = itemoptional.get();

        //System.out.println("IdItem itemid: " + item.get().getId());
        //System.out.println("IdItem contenido: " + item.get().getContenido());

        //return modelMapper.map(item2, ItemDTO.class);
        return modelMapper.map(item, ItemDTO.class);
    };


}
