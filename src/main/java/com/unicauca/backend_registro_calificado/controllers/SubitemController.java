package com.unicauca.backend_registro_calificado.controllers;

import com.unicauca.backend_registro_calificado.domain.SubItemDTO;
import com.unicauca.backend_registro_calificado.services.IsubItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subItem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubitemController {

    private final IsubItemService isubItemService;

    @Autowired
    public SubitemController(IsubItemService isubItemService) {
        this.isubItemService = isubItemService;
    }

    @GetMapping("/getSubItemById")
    public SubItemDTO findSubItemById(@RequestParam String idSubItem) {
        return this.isubItemService.findItemById(idSubItem);
    }

}
