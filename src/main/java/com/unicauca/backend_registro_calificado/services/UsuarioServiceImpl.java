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

        Response<List<UsuarioDTO>> response = new Response<>();

        try{

            List<Usuario> LstUsuarios = iUsuarioRepository.findAll();
            List<UsuarioDTO> usuariosDTO= LstUsuarios.stream().map(usuario ->  modelMapper.map(usuario, UsuarioDTO.class)).collect(Collectors.toList());
            response.setStatus(200);
            response.setUserMessage("Usuarios encontrados con éxito");
            response.setDeveloperMessage("Usuarios encontrados con éxito");
            response.setMoreInfo("http://localhost:8081/api/usuario/findAllUsuarios");
            response.setData(usuariosDTO);

            return response;

        }catch (Exception e){

            response.setStatus(404);
            response.setUserMessage("No se encontraron los usuarios. Causado por: "+e.getMessage());
            response.setDeveloperMessage("No se encontraron los usuarios. Causado por: "+e.getMessage());
            response.setMoreInfo("http://localhost:8081/api/usuario/findAllUsuarios");
            response.setData(null);

            return response;

        }

    }

    @Override
    public Response<UsuarioDTO> findUsuarioById(Long id) {

        Response<UsuarioDTO> response = new Response<>();

        try {

            Usuario usuario = this.iUsuarioRepository.findById(id).get();
            UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
            response.setStatus(200);
            response.setUserMessage("Usuario encontrado con éxito");
            response.setDeveloperMessage("Usuario encontrado con éxito");
            response.setMoreInfo("http://localhost:8081/api/usuraio/findUsuarioById");
            response.setData(usuarioDTO);

            return response;

        }catch (Exception e){

            System.out.println("Error: "+ e);
            response.setStatus(404);
            response.setUserMessage("Usuario no encontrado. Causado por: "+e.getMessage());
            response.setDeveloperMessage("Usuario no encontrado. Causado por: "+e.getMessage());
            response.setMoreInfo("http://localhost:8081/api/usuraio/findUsuarioById");
            response.setData(null);

            return response;

        }
    }

    @Override
    public Response<UsuarioDTO> saveUsuario(UsuarioDTO usuario) {

        Response<UsuarioDTO> response = new Response<>();

        try{

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

            return response;

        }catch (Exception e){

            response.setStatus(404);
            response.setUserMessage("Error al guardar usuario. Causado por: " +e.getMessage());
            response.setDeveloperMessage("Error al guardar usuario. Causado por: " +e.getMessage());
            response.setMoreInfo("http://localhost:8081/api/usuraio/saveUsuario");
            response.setData(null);

            return response;

        }
    }

    @Override
    public Response<UsuarioDTO> updateUsuario(Long id, UsuarioDTO usuario) {

        Response<UsuarioDTO> response = new Response<>();

        try{

            Usuario usuarioEntity = this.iUsuarioRepository.findById(id).get();
            Usuario usuarioEntityAux = modelMapper.map(usuario, Usuario.class);
            usuarioEntity.setNombre(usuarioEntityAux.getNombre());
            usuarioEntity.setCorreo(usuarioEntityAux.getCorreo());

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

            return response;

        }catch (Exception e){

            response.setStatus(404);
            response.setUserMessage("Error al actualizar usuario. Causado por: " +e.getMessage());
            response.setDeveloperMessage("Error al actualizar usuario. Causado por: " +e.getMessage());
            response.setMoreInfo("http://localhost:8081/api/usuraio/updateUsuario");
            response.setData(null);

            return response;

        }
    }

    @Override
    public Response<Boolean> disableOrEnableUsuario(Long id) {

        Response<Boolean> response = new Response<>();

        try {

            Usuario userEntity = this.iUsuarioRepository.findById(id).get();

            if (userEntity.getEstado() != null) {

                userEntity.setEstado(!userEntity.getEstado());
                this.iUsuarioRepository.save(userEntity);
                response.setStatus(200);
                response.setUserMessage("Estado modificado con éxito");
                response.setDeveloperMessage("Estado modificado con éxito");
                response.setMoreInfo("http://localhost:8081/api/usuario/disableOrEnableUsuario/{id}");
                response.setData(true);

            } else {

                response.setStatus(500);
                response.setUserMessage("Error al modificar el estado");
                response.setDeveloperMessage("Error al modificar el estado");
                response.setMoreInfo("http://localhost:8081/api/usuario/disableOrEnableUsuario/{id}");
                response.setData(false);

            }

            return response;

        }catch (Exception e) {

            response.setStatus(404);
            response.setUserMessage("No se encontró el usuario. Causado por: " + e.getMessage());
            response.setDeveloperMessage("No se encontró el usuario. Causado por: " + e.getMessage());
            response.setMoreInfo("http://localhost:8081/api/usuario/DisableUsuario/{id}");
            response.setData(false);

            return response;

        }

    }

    @Override
    public Response<Boolean> enableUsuario(Long id) {

        Response<Boolean> response = new Response<>();
        try{

            Usuario userEntity = this.iUsuarioRepository.findById(id).get();

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

            return response;

        }catch (Exception e) {

            response.setStatus(404);
            response.setUserMessage("No se encontró el usuario. Causado por: " + e.getMessage());
            response.setDeveloperMessage("No se encontró el usuario. Causado por: " + e.getMessage());
            response.setMoreInfo("http://localhost:8081/api/usuario/enableUsuario/{id}");
            response.setData(false);

            return response;

        }
    }

    @Override
    public Response<UsuarioDTO> findUsuarioByEmail(String email) {

        Response<UsuarioDTO> response = new Response<>();

        try {

            Usuario usuario = this.iUsuarioRepository.findByCorreo(email);
            UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
            response.setStatus(200);
            response.setUserMessage("Usuario encontrado con éxito");
            response.setDeveloperMessage("Usuario encontrado con éxito");
            response.setMoreInfo("http://localhost:8081/api/usuraio/findUsuarioByEmail");
            response.setData(usuarioDTO);

            return response;

        }catch (Exception e){

            response.setStatus(404);
            response.setUserMessage("Usuario no encontrado. Causado por: "+e.getMessage());
            response.setDeveloperMessage("Usuario no encontrado. Causado por: "+e.getMessage());
            response.setMoreInfo("http://localhost:8081/api/usuraio/findUsuarioByEmail");
            response.setData(null);

            return response;

        }
    }
}
