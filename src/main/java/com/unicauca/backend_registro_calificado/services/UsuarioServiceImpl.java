package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.domain.UsuarioDTO;
import com.unicauca.backend_registro_calificado.model.Usuario;
import com.unicauca.backend_registro_calificado.repository.IUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    IUsuarioRepository iUsuarioRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Response<List<UsuarioDTO>> findAllUsuarios() {
        List<Usuario> LstUsuarios = iUsuarioRepository.findAll();
        Response<List<UsuarioDTO>> response = new Response<>();
        if(LstUsuarios.isEmpty()){
            response.setStatus(404);
            response.setUserMessage("No se encontraron los usuarios");
            response.setDeveloperMessage("No se encontraron los usuarios");
            response.setMoreInfo("http://localhost:8081/api/usuario/findAllUsuarios");
            response.setData(null);
        }else{
            List<UsuarioDTO> usuariosDTO= LstUsuarios.stream().map(usuario ->  modelMapper.map(usuario, UsuarioDTO.class)).collect(Collectors.toList());
            response.setStatus(200);
            response.setUserMessage("Usuarios encontrados con éxito");
            response.setDeveloperMessage("Usuarios encontrados con éxito");
            response.setMoreInfo("http://localhost:8081/api/usuario/findAllUsuarios");
            response.setData(usuariosDTO);
        }
        return response;
    }

    @Override
    public Response<UsuarioDTO> findUsuarioById(Long id) {
        Usuario usuario = this.iUsuarioRepository.findById(id).get();
        Response<UsuarioDTO> response = new Response<>();
        if(usuario != null){
            UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
            response.setStatus(200);
            response.setUserMessage("Usuario encontrado con éxito");
            response.setDeveloperMessage("Usuario encontrado con éxito");
            response.setMoreInfo("http://localhost:8081/api/usuraio/findUsuarioById");
            response.setData(usuarioDTO);
        }else {
            response.setStatus(404);
            response.setUserMessage("Usuario no encontrado");
            response.setDeveloperMessage("Usuario no encontrado");
            response.setMoreInfo("http://localhost:8081/api/usuraio/findUsuarioById");
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response<UsuarioDTO> saveUsuario(UsuarioDTO usuario) {
        System.out.println("rol"+usuario.getRol().getRolNombre());
        Response<UsuarioDTO> response = new Response<>();
        if(usuario != null){
            Usuario usuarioEntity = modelMapper.map(usuario, Usuario.class);
            usuarioEntity.setEstado(true);
            usuarioEntity.setContrasena(passwordEncoder.encode(usuarioEntity.getContrasena()));
            this.iUsuarioRepository.save(usuarioEntity);
            UsuarioDTO usuarioDTO = this.modelMapper.map(usuarioEntity, UsuarioDTO.class);
            response.setStatus(200);
            response.setUserMessage("Usuario guardado con éxito");
            response.setDeveloperMessage("Usuario guardado con éxito");
            response.setMoreInfo("http://localhost:8081/api/usuraio/saveUsuario");
            response.setData(usuarioDTO);
        }else {
            response.setStatus(404);
            response.setUserMessage("Error al guardar usuario");
            response.setDeveloperMessage("Error al guardar usuario");
            response.setMoreInfo("http://localhost:8081/api/usuraio/saveUsuario");
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response<UsuarioDTO> updateUsuario(Long id, UsuarioDTO usuario) {
        Response<UsuarioDTO> response = new Response<>();
        if(usuario != null){
            Usuario usuarioEntity = this.iUsuarioRepository.findById(id).get();
            Usuario usuarioEntity1 = modelMapper.map(usuario, Usuario.class);
            usuarioEntity.setNombre(usuarioEntity1.getNombre());
            usuarioEntity.setCorreo(usuarioEntity1.getCorreo());
            if(!passwordEncoder.matches(usuarioEntity.getContrasena(), usuarioEntity.getContrasena())) {
                usuarioEntity.setContrasena(passwordEncoder.encode(usuarioEntity.getContrasena()));
            }
            this.iUsuarioRepository.save(usuarioEntity);
            response.setStatus(200);
            response.setUserMessage("Usuario actualizado con éxito");
            response.setDeveloperMessage("Usuario actualizado con éxito");
            response.setMoreInfo("http://localhost:8081/api/usuraio/updateUsuario");
            UsuarioDTO usuarioDTO = this.modelMapper.map(usuarioEntity, UsuarioDTO.class);
            response.setData(usuarioDTO);
        }else{
            response.setStatus(404);
            response.setUserMessage("El usuario no se ha podido actualizar");
            response.setDeveloperMessage("El usuario no se ha podido actualizar");
            response.setMoreInfo("http://localhost:8081/api/usuraio/updateUsuario");
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response<Boolean> disableUsuario(Long id) {
        Usuario userEntity = this.iUsuarioRepository.findById(id).get();
        Response<Boolean> response = new Response<>();
        if (userEntity != null) {
            if (userEntity.getEstado() == true) {
                // el usuario aun no esta deshabilitado
                userEntity.setEstado(false);
                this.iUsuarioRepository.save(userEntity);
                response.setStatus(200);
                response.setUserMessage("Usuario deshabilitado con éxito");
                response.setMoreInfo("http://localhost:8081/api/usuario/DisableUsuario/{id}");
                response.setData(true);
            } else {
                // el usuario ya esta deshabilitado
                response.setStatus(500);
                response.setUserMessage("El usuario ya esta deshabilitado");
                response.setMoreInfo("http://localhost:8081/api/usuario/DisableUsuario/{id}");
                response.setData(false);
            }
        }
        return response;
    }

    @Override
    public Response<Boolean> enableUsuario(Long id) {
        Usuario userEntity = this.iUsuarioRepository.findById(id).get();
        Response<Boolean> response = new Response<>();
        if (userEntity != null) {
            if (!userEntity.getEstado()) {
                userEntity.setEstado(true);
                this.iUsuarioRepository.save(userEntity);
                response.setStatus(200);
                response.setUserMessage("Usuario habilitado con éxito");
                response.setMoreInfo("http://localhost:8081/api/usuario/enableUsuario/{id}");
                response.setData(true);
            } else {
                response.setStatus(500);
                response.setUserMessage("El usuario ya esta habilitado");
                response.setMoreInfo("http://localhost:8081/api/usuario/enableUsuario/{id}");
                response.setData(false);
            }
        }
        return response;
    }
}
