package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.model.Item;
import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import com.unicauca.backend_registro_calificado.repository.IitemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements IitemService{

    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    private final IitemRepository iitemRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ItemServiceImpl(IitemRepository iitemRepository, ModelMapper modelMapper) {
        this.iitemRepository = iitemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<List<ItemDTO>> findAllItemsByIdRegistroCalificado(String idRegCalificado) {
        return null;
    }

    @Override
    //public Response<ItemDTO> findItemById(Integer  IdItem){
    public ItemDTO findItemById(Integer  IdItem){
        System.out.println("IdItem: " + IdItem);

        Optional<Item> itemoptional = this.iitemRepository.findById(IdItem);
        Item item = itemoptional.get();

        //System.out.println("IdItem itemid: " + item.get().getId());
        //System.out.println("IdItem contenido: " + item.get().getContenido());

        //return modelMapper.map(item2, ItemDTO.class);
        return modelMapper.map(item, ItemDTO.class);
    };

    @Override
    public Response<ItemDTO> updateItem(Integer  id, ItemDTO itemDTO){
        Response<ItemDTO> response = new Response<>();
        logger.debug("Miremos esto: {}", itemDTO.toString());
        System.out.println("Miremos esto: " + itemDTO.toString());
        // Busco el environment a actualizar
        Item item = null;
        item = this.iitemRepository.findItemById(id);
        Item itemUpdate = modelMapper.map(itemDTO, Item.class);

        if (item != null) {

            // actualiza el subItem
            item.setId(itemUpdate.getId());
            item.setContenido(itemUpdate.getContenido());
            item.setGuia(itemUpdate.getGuia());
            this.iitemRepository.save(item);
            ItemDTO item1 = modelMapper.map(item, ItemDTO.class);
            response.setStatus(200);
            response.setUserMessage("Subitem actualizado");
            response.setDeveloperMessage("Subitem actualizado");
            response.setMoreInfo("localhost:8080/api/item");
            response.setErrorCode("");
            response.setData(item1);
            logger.debug("Finish update Subitem Business");

        } else {

            response.setStatus(400);
            response.setUserMessage("Subitem no actualizado");
            response.setDeveloperMessage("Subitem actualizado");
            response.setMoreInfo("localhost:8080/api/subitem");
            response.setErrorCode("Id del subitem no encontrado");
            response.setData(null);
            logger.debug("Finish update Subitem Business");
        }
        return response;
    }

    @Override
    public Response<ItemDTO> createItem(ItemDTO itemDTO) {
        logger.debug("Init createItem Business: {}", itemDTO.toString());
        Response<ItemDTO> response = new Response<>();
        Item item = modelMapper.map(itemDTO, Item.class);
        ItemDTO itemDTO1 = modelMapper.map(iitemRepository.save(item), ItemDTO.class);
        response.setStatus(200);
        response.setUserMessage("Item creado exitosamente");
        response.setDeveloperMessage("Item creado exitosamente");
        response.setMoreInfo("localhost:8080/api/item");
        response.setErrorCode("");
        response.setData(itemDTO1);
        logger.debug("Finish createItem");
        return response;

    }

}
