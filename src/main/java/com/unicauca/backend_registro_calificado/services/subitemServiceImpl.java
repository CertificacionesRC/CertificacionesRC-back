package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.model.SubItem;
import com.unicauca.backend_registro_calificado.domain.SubItemDTO;
import com.unicauca.backend_registro_calificado.repository.ISubItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicauca.backend_registro_calificado.domain.Response;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Service
public class subitemServiceImpl implements IsubItemService {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(subitemServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ISubItemRepository subitemRepository;

    @Override
    public Response<SubItemDTO> updateSubItem(SubItemDTO subitemDTO, String subitemId) {
        Response<SubItemDTO> response = new Response<>();
        logger.debug("Miremos esto: {}", subitemDTO.toString());
        System.out.println("Miremos esto: " + subitemDTO.toString());
        // Busco el environment a actualizar
        SubItem subitem = null;
        subitem = this.subitemRepository.findSubItemById(subitemId);
        SubItem subItemUpdate = modelMapper.map(subitemDTO, SubItem.class);

        if (subitem != null) {

            // actualiza el subItem
            subitem.setId(subItemUpdate.getId());
            subitem.setContenido(subItemUpdate.getContenido());
            subitem.setGuia(subItemUpdate.getGuia());
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
    public SubItemDTO findItemById(String IdSubItem) {
        Optional<SubItem> Subitemoptional = this.subitemRepository.findById(IdSubItem);
        SubItem Subitem = Subitemoptional.get();

        return modelMapper.map(Subitem, SubItemDTO.class);
    }
}