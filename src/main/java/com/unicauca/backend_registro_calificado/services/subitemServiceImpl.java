package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.model.Item;
import com.unicauca.backend_registro_calificado.model.SubItem;
import com.unicauca.backend_registro_calificado.domain.SubItemDTO;
import com.unicauca.backend_registro_calificado.repository.ISubItemRepository;
import com.unicauca.backend_registro_calificado.repository.IitemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicauca.backend_registro_calificado.domain.Response;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class subitemServiceImpl implements IsubItemService {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(subitemServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    //@Autowired
    private ISubItemRepository subitemRepository;

    public subitemServiceImpl(ISubItemRepository subitemRepository) {
        this.subitemRepository = subitemRepository;

    }

    @Override
    public List<SubItemDTO> findAllSubItem() {
        System.out.println("ProgramServiceImpl.findAllProgram");
        List<SubItem> subItems = this.subitemRepository.findAll();

        List<SubItemDTO> SubitemDTOS = subItems.stream().map(subItem ->  modelMapper.map(subItem, SubItemDTO.class)).collect(Collectors.toList());
        return SubitemDTOS;
    }

    @Override
    public List<SubItemDTO> findSubItemsByItem_IdAndParent_IdIsNull(Integer idItem) {
        List<SubItem> subItems = this.subitemRepository.findSubItemsByItem_IdAndParentSubItem_Id(idItem, null);
        List<SubItemDTO> SubitemDTOS = subItems.stream().map(
                subItem -> modelMapper.map(subItem, SubItemDTO.class)).collect(Collectors.toList());
        return SubitemDTOS;
    }

    @Override
    public Response<SubItemDTO> findSubItemById(Integer IdSubItem) {

        SubItem subitem = this.subitemRepository.findSubItemById(IdSubItem);
        SubItemDTO subitemDTO1 = modelMapper.map(subitem, SubItemDTO.class);
        Response<SubItemDTO> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Subitem successfully");
        response.setDeveloperMessage("Subitem successfully");
        response.setMoreInfo("localhost:8080/api/subitem");
        response.setErrorCode("");
        response.setData(subitemDTO1);
        return response;

        /*
        Optional<SubItem> subItemOptional = this.subitemRepository.findById(IdSubItem);
        SubItem subItem = subItemOptional.get();
        return modelMapper.map(subItem, SubItemDTO.class);

         */
    }

    @Override
    public Response<SubItemDTO> updateSubItem(Integer id, SubItemDTO subItemDTO) {
        Response<SubItemDTO> response = new Response<>();
        logger.debug("Miremos esto: {}", subItemDTO.toString());
        System.out.println("Miremos esto: " + subItemDTO.toString());
        // Busco el environment a actualizar
        SubItem subitem = null;
        subitem = this.subitemRepository.findSubItemById(id);
        SubItem subItemUpdate = modelMapper.map(subItemDTO, SubItem.class);

        if (subitem != null) {

            // actualiza el subItem
            //subitem.setId(subItemUpdate.getId());
            subitem.setContenido(subItemUpdate.getContenido());
            subitem.setGuia(subItemUpdate.getGuia());
            subitem.setNombre(subItemUpdate.getNombre());

            this.subitemRepository.save(subitem);
            SubItemDTO subitem1 = modelMapper.map(subitem, SubItemDTO.class);
            response.setStatus(200);
            response.setUserMessage("Subitem actualizado");
            response.setDeveloperMessage("Subitem actualizado");
            response.setMoreInfo("localhost:8080/api/subitem");
            response.setErrorCode("");
            response.setData(subitem1);
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
    public Response<SubItemDTO> createSubItem(SubItemDTO subItemDTO) {
        logger.debug("Init createSubItem: {}", subItemDTO.toString());
        Response<SubItemDTO> response = new Response<>();
        SubItem subItem = modelMapper.map(subItemDTO, SubItem.class);
        SubItemDTO subItemDTO1 = modelMapper.map(subitemRepository.save(subItem), SubItemDTO.class);
        response.setStatus(200);
        response.setUserMessage("SubItem creado exitosamente");
        response.setDeveloperMessage("SubItem creado exitosamente");
        response.setMoreInfo("localhost:8080/api/subitem");
        response.setErrorCode("");
        response.setData(subItemDTO1);
        logger.debug("Finish createSubItem Business");

        return response;
    }


}