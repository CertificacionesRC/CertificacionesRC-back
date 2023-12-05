package com.unicauca.backend_registro_calificado.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.domain.UsuarioDTO;
import com.unicauca.backend_registro_calificado.services.IUsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    IUsuarioService iUsuarioService;
    @Secured("ADMIN")
    @GetMapping("/findAllUsuarios")
    public Response<List<UsuarioDTO>> findAllUsuarios(){
        return this.iUsuarioService.findAllUsuarios();
    }
    @Secured("ADMIN")
    @GetMapping("/findUsuarioById/{id}")
    public Response<UsuarioDTO> findUsuarioById(@PathVariable Long id){
        return this.iUsuarioService.findUsuarioById(id);
    }
    @Secured("ADMIN")
    @PostMapping("/saveUsuario")
    public Response<UsuarioDTO> saveUsuario(@RequestBody UsuarioDTO usuario){
        /*
        Response<UsuarioDTO> prueba = iUsuarioService.saveUsuario(usuario);
        HttpHeaders headers = new HttpHeaders();

        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
        headers.add("Access-Control-Max-Age", "3600");
        headers.add("Access-Control-Expose-Headers", "*");
        return ResponseEntity.ok().headers(headers).body(prueba);

         */
        return this.iUsuarioService.saveUsuario(usuario);
    }
    @Secured("ADMIN")
    @PatchMapping("/updateUsuario/{id}")
    public  Response<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuario){
        return this.iUsuarioService.updateUsuario(id, usuario);
    }
    @Secured("ADMIN")
    @PatchMapping("/disableOrEnableUsuario/{id}")
    public Response<Boolean> disableOrEnableUsuario(@PathVariable Long id){
        return  this.iUsuarioService.disableOrEnableUsuario(id);
    }

//    @Secured("ADMIN")
    @GetMapping("/findUsuarioByEmail/{email}")
    public Response<UsuarioDTO> findUsuarioByEmail(@PathVariable String email){
        return this.iUsuarioService.findUsuarioByEmail(email);
    }
}
