package com.unicauca.backend_registro_calificado.controllers;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.domain.SubItemDTO;
import com.unicauca.backend_registro_calificado.services.IsubItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/subItem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubItemController {

    private final IsubItemService isubItemService;

    @Autowired
    public SubItemController(IsubItemService isubItemService) {
        this.isubItemService = isubItemService;
    }

    @GetMapping("/getSubItemById")
    public Response<SubItemDTO> findSubItemById(@RequestParam Integer idSubItem) {
        //return  null;
        return this.isubItemService.findSubItemById(idSubItem);
    }
    @GetMapping("/getSubitemsbyParentId")
    public Response<List<SubItemDTO>> findAllByParentId(@RequestParam Integer parentId) {
        return this.isubItemService.findAllByParentId(parentId);
    }

    @RequestMapping(value = "/updateSubitem/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ResponseBody
    public Response<SubItemDTO> updateSubItem(@RequestBody SubItemDTO SubItemDTO,
                                        @PathVariable Integer id) {
        System.out.println("llega al controlador de update");
        return this.isubItemService.updateSubItem(id, SubItemDTO);
    }

    @GetMapping("/getAllSubItem")
    public ResponseEntity<List<SubItemDTO>> getAllSubitem() {
        return ResponseEntity.status(HttpStatus.OK).body(this.isubItemService.findAllSubItem());
    }




    @PostMapping("/postSubItem")
    public Response<SubItemDTO> createSubItem(@RequestBody SubItemDTO subItemDTO) {
        return this.isubItemService.createSubItem(subItemDTO);
    }



}
