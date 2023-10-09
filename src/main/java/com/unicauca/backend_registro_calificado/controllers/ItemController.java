package com.unicauca.backend_registro_calificado.controllers;

import org.springframework.web.bind.annotation.*;

import com.unicauca.backend_registro_calificado.services.IitemService;
import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemController {
    private final IitemService iitemService;

    @Autowired
    public ItemController(IitemService iitemService) {
        this.iitemService = iitemService;
    }

    @GetMapping("/getAllByRegitroCalificado")
    public Response<List<ItemDTO>> findAllByRegitroCalificado(@RequestParam String idRegistroCalificado) {
        return this.iitemService.findAllItemsByIdRegistroCalificado(idRegistroCalificado);
    }


}
