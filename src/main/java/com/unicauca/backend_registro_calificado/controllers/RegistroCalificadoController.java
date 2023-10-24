package com.unicauca.backend_registro_calificado.controllers;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.services.IRegistroCalificadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.unicauca.backend_registro_calificado.domain.Response;

@RestController
@RequestMapping("/registrocalificado")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistroCalificadoController {

    private final IRegistroCalificadoService registroCalificadoBusiness;

    public RegistroCalificadoController(IRegistroCalificadoService registroCalificadoBusiness) {
        this.registroCalificadoBusiness = registroCalificadoBusiness;
    }

    @PostMapping()
    public Response<RegistroCalificadoDTO> createRegistro(@RequestBody RegistroCalificadoDTO registroCalificadoDTO) {
        return this.registroCalificadoBusiness.createRegistroCalificado(registroCalificadoDTO);
    }


}
