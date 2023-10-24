package com.unicauca.backend_registro_calificado.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.unicauca.backend_registro_calificado.services.IitemService;
import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class    ItemController {
    private final IitemService iitemService;

    @Autowired
    public ItemController(IitemService iitemService) {
        this.iitemService = iitemService;
    }

    @GetMapping("/getAllByRegitroCalificado")
    public Response<List<ItemDTO>> findAllByRegitroCalificado(@RequestParam String idRegistroCalificado) {
        return this.iitemService.findAllItemsByIdRegistroCalificado(idRegistroCalificado);
    }

    @GetMapping("/getItemById")
    //public Response<ItemDTO> findItemById(@RequestParam String idItem) {
    public ItemDTO findItemById(@RequestParam Integer  idItem) {
        return this.iitemService.findItemById(idItem);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ResponseBody
    public Response<ItemDTO> updateItem(@RequestBody ItemDTO ItemDTO,
                                                      @PathVariable Integer id) {
        System.out.println("llega al controlador de update");
        return this.iitemService.updateItem(id, ItemDTO);
    }
}
