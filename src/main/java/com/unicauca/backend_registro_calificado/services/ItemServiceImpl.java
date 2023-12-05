package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.model.Item;
import com.unicauca.backend_registro_calificado.model.SubItem;
import com.unicauca.backend_registro_calificado.model.enums.EstadoItem;
import com.unicauca.backend_registro_calificado.repository.IitemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Response<ItemDTO> findItemById(Integer  IdItem){

        Item item = this.iitemRepository.findById(IdItem).get();

        ItemDTO itemDTO1 = modelMapper.map(item, ItemDTO.class);

        Response<ItemDTO> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Item successfully");
        response.setDeveloperMessage("Item successfully");
        response.setMoreInfo("localhost:8080/api/Item");
        response.setErrorCode("");
        response.setData(itemDTO1);

        return response;
        /*
        System.out.println("IdItem: " + IdItem);

        Optional<Item> itemoptional = this.iitemRepository.findById(IdItem);
        Item item = itemoptional.get();
        */
        //System.out.println("IdItem itemid: " + item.get().getId());
        //System.out.println("IdItem contenido: " + item.get().getContenido());

        //return modelMapper.map(item2, ItemDTO.class);
        //return modelMapper.map(item, ItemDTO.class);
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
            //item.setId(itemUpdate.getId());
            item.setContenido(itemUpdate.getContenido());
//            item.setGuia(itemUpdate.getGuia());
//            item.setNombre(itemUpdate.getNombre());

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
            response.setUserMessage("item no actualizado");
            response.setDeveloperMessage("item actualizado");
            response.setMoreInfo("localhost:8080/api/item");
            response.setErrorCode("Id del item no encontrado");
            response.setData(null);
            logger.debug("Finish update item Business");
        }
        return response;
    }

    @Override
    public List<ItemDTO> findAllItem() {
        List<Item> items = this.iitemRepository.findAll();

        //Elimino repetidos
        List<SubItem> itemsSinRepetidos = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < items.get(i).getSubItems().size(); j++) {
                for (int k = 0; k < items.get(i).getSubItems().get(j).getSubItems().size(); k++) {
                    for (int l = 0; l < items.get(i).getSubItems().size(); l++) {
                        if (items.get(i).getSubItems().get(j).getSubItems().get(k).getId() == items.get(i).getSubItems().get(l).getId()) {
                            itemsSinRepetidos.add(items.get(i).getSubItems().get(j).getSubItems().get(k));
                            items.get(i).getSubItems().remove(l);
                        }
                    }
                }
            }
        }

        List<ItemDTO> itemDTOS = items.stream().map(item ->  modelMapper.map(item, ItemDTO.class)).collect(Collectors.toList());
        return itemDTOS;
    }

    @Override
    public Response<ItemDTO> createItem(ItemDTO itemDTO) {
        logger.debug("Init createItem Business: {}", itemDTO.toString());
        Response<ItemDTO> response = new Response<>();
        Item item = modelMapper.map(itemDTO, Item.class);
        item.setEstado(EstadoItem.EnProceso);
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

    @Override
    public ResponseEntity<?> updateStateItem(Integer idItem) {
        try{
            Item item = this.iitemRepository.findById(idItem).get();
            boolean todosEnProceso = true;
            for (SubItem subitem : item.getSubItems()) {
                if ("EnProceso".equalsIgnoreCase(String.valueOf(subitem.getEstado()))) {
                    todosEnProceso = false;
                    break;
                }
            }
            if (todosEnProceso) {
                item.setEstado(EstadoItem.Completado);
                this.iitemRepository.save(item);
                return new ResponseEntity("Estado actualizado con exito", HttpStatus.OK);
            }
            return new ResponseEntity("El estado no se ha podido actualizar porque algunos subitems no han sido completados", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("El estado no se ha podido actualizar. Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
