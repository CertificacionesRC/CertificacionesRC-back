package com.unicauca.backend_registro_calificado.controllers;
import com.unicauca.backend_registro_calificado.domain.ObservacionDTO;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import com.unicauca.backend_registro_calificado.services.IDocumentoService;
import com.unicauca.backend_registro_calificado.services.IRegistroCalificadoService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.unicauca.backend_registro_calificado.domain.Response;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/registrocalificado")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistroCalificadoController {

    private final IRegistroCalificadoService registroCalificadoBusiness;
    private final IDocumentoService documentoService;

    public RegistroCalificadoController(IRegistroCalificadoService registroCalificadoBusiness, IDocumentoService documentoService) {
        this.registroCalificadoBusiness = registroCalificadoBusiness;
        this.documentoService = documentoService;
    }

    @PostMapping()
    public Response<RegistroCalificadoDTO> createRegistro(@RequestBody RegistroCalificadoDTO registroCalificadoDTO) {
        return this.registroCalificadoBusiness.createRegistroCalificado(registroCalificadoDTO);
    }
    @Secured("ADMIN")
    @PostMapping("/updateStateRegistroCalificado")
    public ResponseEntity<?> updateStateRegistroCalificado(@RequestBody ObservacionDTO observacion, @RequestParam EstadoRegistroCal estado) {
        return this.registroCalificadoBusiness.updateStateRegistroCalificado(observacion, estado);
    }
    @Secured("ADMIN")
    @GetMapping("/findAllByEstado")
    public Response<List<RegistroCalificadoDTO>> findAllByEstado(@RequestParam EstadoRegistroCal estado){
        return this.registroCalificadoBusiness.findAllByEstado(estado);
    }

    @Secured("ADMIN")
    @GetMapping("/findAllByDate")
    public Response<List<RegistroCalificadoDTO>> findAllByDate(@RequestParam String fechaInicio, @RequestParam String fechaFin) {
        try {
            return this.registroCalificadoBusiness.findAllByDate(fechaInicio, fechaFin);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getDocumento")
    public ResponseEntity<byte[]> downloadWordFile(@RequestParam Integer IdRegistroCalificado) throws Exception {
        return documentoService.downloadWordFile(IdRegistroCalificado);
    }
}
