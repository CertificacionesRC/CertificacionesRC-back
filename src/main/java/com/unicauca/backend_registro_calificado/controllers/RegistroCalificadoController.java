package com.unicauca.backend_registro_calificado.controllers;
import com.unicauca.backend_registro_calificado.domain.ObservacionDTO;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.model.ProgramaAcademico;
import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import com.unicauca.backend_registro_calificado.repository.IProgramaAcademicoRepo;
import com.unicauca.backend_registro_calificado.repository.IRegistroCalifRepository;
import com.unicauca.backend_registro_calificado.services.IRegistroCalificadoService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import java.net.MalformedURLException;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.unicauca.backend_registro_calificado.domain.Response;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;




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
    public ResponseEntity<byte[]> downloadWordFile() throws Exception {

        return registroCalificadoBusiness.downloadWordFile();

    }
}
