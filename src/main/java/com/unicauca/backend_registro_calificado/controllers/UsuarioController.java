package com.unicauca.backend_registro_calificado.controllers;

import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.domain.UsuarioDTO;
import com.unicauca.backend_registro_calificado.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
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
        return this.iUsuarioService.saveUsuario(usuario);
    }
    @Secured("ADMIN")
    @PatchMapping("/updateUsuario/{id}")
    public  Response<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuario){
        return this.iUsuarioService.updateUsuario(id, usuario);
    }
    @Secured("ADMIN")
    @PatchMapping("/disableUsuario/{id}")
    public Response<Boolean> disableUusario(@PathVariable Long id){
        return  this.iUsuarioService.disableUsuario(id);
    }
}
