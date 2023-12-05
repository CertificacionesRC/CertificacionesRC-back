package com.unicauca.backend_registro_calificado.controllers;

import com.unicauca.backend_registro_calificado.domain.ProgramaAcademicoDTO;
import com.unicauca.backend_registro_calificado.services.IProgramaAcademicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programaAcademico")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProgramaAcademicoController {
   private final IProgramaAcademicoService service;
    public ProgramaAcademicoController(IProgramaAcademicoService service){
        this.service = service;
    }
    @GetMapping("/findAll")
    public List<ProgramaAcademicoDTO> findAll(){
        return service.findAll();
    }
}
