package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.model.SubItem;
import com.unicauca.backend_registro_calificado.domain.SubItemDTO;
import com.unicauca.backend_registro_calificado.model.enums.EstadoItem;
import com.unicauca.backend_registro_calificado.repository.ISubItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.unicauca.backend_registro_calificado.domain.Response;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class subitemServiceImpl implements IsubItemService {
    private static final Logger logger = LoggerFactory.getLogger(subitemServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;
    private ISubItemRepository subitemRepository;
    public subitemServiceImpl(ISubItemRepository subitemRepository) {
        this.subitemRepository = subitemRepository;
    }
    @Override
    public List<SubItemDTO> findAllSubItem() {

        List<SubItem> subItems = this.subitemRepository.findAll();
        List<SubItemDTO> SubitemDTOS = subItems.stream().map(subItem ->  modelMapper.map(subItem, SubItemDTO.class)).collect(Collectors.toList());
        return SubitemDTOS;

    }

    @Override
    public Response<List<SubItemDTO>> findAllByParentId(Integer parentId) {

        List<SubItem> subitemslist = this.subitemRepository.findAllByParentId(parentId);
        Response<List<SubItemDTO>> response = new Response<>();
        List<SubItemDTO> SubItemsDTOHijos = subitemslist.stream().map(subItem ->  modelMapper.map(subItem, SubItemDTO.class)).collect(Collectors.toList());

        if(SubItemsDTOHijos.size()>0) {

            response.setStatus(200);
            response.setUserMessage("List of subitems Finded successfully");
            response.setDeveloperMessage("List of subitems Finded successfully");
            response.setMoreInfo("localhost:8081/api/subitem(toDO)");
            response.setErrorCode("");
            response.setData(SubItemsDTOHijos);

        }else{

            response.setStatus(400);
            response.setUserMessage("List of subitems not found");
            response.setDeveloperMessage("List of subitems not found");
            response.setMoreInfo("localhost:8081/api/subitem(toDO)");
            response.setErrorCode("400");
            response.setData(SubItemsDTOHijos);

        }

        return response;

    }

    @Override
    public ResponseEntity<?> updateStateSubItem(Integer idSubitem) {

        try{

            SubItem subitem = this.subitemRepository.findById(idSubitem).get();
            subitem.setEstado(EstadoItem.Completado);
            SubItem prueba = this.subitemRepository.save(subitem);

            return new ResponseEntity("Estado actualizado con exito", HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity("El estado no se ha podido actualizar. Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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

    }

    @Override
    public Response<SubItemDTO> updateSubItem(Integer id, SubItemDTO subItemDTO) {

        Response<SubItemDTO> response = new Response<>();

        SubItem subitem = null;
        subitem = this.subitemRepository.findSubItemById(id);
        SubItem subItemUpdate = modelMapper.map(subItemDTO, SubItem.class);

        if (subitem != null) {

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

        } else {

            response.setStatus(400);
            response.setUserMessage("Subitem no actualizado");
            response.setDeveloperMessage("Subitem actualizado");
            response.setMoreInfo("localhost:8080/api/subitem");
            response.setErrorCode("Id del subitem no encontrado");
            response.setData(null);

        }
        return response;
    }

    @Override
    public Response<SubItemDTO> createSubItem(SubItemDTO subItemDTO) {

        Response<SubItemDTO> response = new Response<>();
        SubItem subItem = modelMapper.map(subItemDTO, SubItem.class);
        subItem.setEstado(EstadoItem.EnProceso);
        SubItemDTO subItemDTO1 = modelMapper.map(subitemRepository.save(subItem), SubItemDTO.class);
        response.setStatus(200);
        response.setUserMessage("SubItem creado exitosamente");
        response.setDeveloperMessage("SubItem creado exitosamente");
        response.setMoreInfo("localhost:8080/api/subitem");
        response.setErrorCode("");
        response.setData(subItemDTO1);

        return response;

    }

}