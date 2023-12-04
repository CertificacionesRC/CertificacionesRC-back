package com.unicauca.backend_registro_calificado.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.unicauca.backend_registro_calificado.services.IitemService;
import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class    ItemController {
    private final IitemService iitemService;

    @Autowired
    public ItemController(IitemService iitemService) {
        this.iitemService = iitemService;
    }

    @PostMapping("/postItem")
    public Response<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) {
        return this.iitemService.createItem(itemDTO);
    }

    @GetMapping("/getAllItem")
    public ResponseEntity<List<ItemDTO>> getAllItem() {
        return ResponseEntity.status(HttpStatus.OK).body(this.iitemService.findAllItem());
    }

    @GetMapping("/getItemById")
    //public Response<ItemDTO> findItemById(@RequestParam String idItem) {
    public Response<ItemDTO> findItemById(@RequestParam Integer  idItem) {
        return this.iitemService.findItemById(idItem);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ResponseBody
    public Response<ItemDTO> updateItem(@RequestBody ItemDTO ItemDTO,
                                                      @PathVariable Integer id) {
        System.out.println("llega al controlador de update");
        return this.iitemService.updateItem(id, ItemDTO);
    }
    @Secured("COORDINADOR")
    @PatchMapping(value = "/updateState/{id}", produces = "application/json")
    public ResponseEntity<?> updateState(@PathVariable Integer id){
        return this.iitemService.updateStateItem(id);
    }
}
