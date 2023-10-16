package com.unicauca.backend_registro_calificado.controllers;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.domain.SubItemDTO;
import com.unicauca.backend_registro_calificado.services.IsubItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public SubItemDTO findSubItemById(@RequestParam String idSubItem) {
        //return  null;
        return this.isubItemService.findSubItemById(idSubItem);
    }

    @PutMapping("/updateItem")
    public Response<SubItemDTO> updateSubItem(@RequestParam String id, @RequestParam SubItemDTO subItemDTO) {
        return this.isubItemService.updateSubItem(id, subItemDTO);
    }
}
