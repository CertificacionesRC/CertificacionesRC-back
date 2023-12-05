package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.domain.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    public Response<List<UsuarioDTO>> findAllUsuarios();
    public Response<UsuarioDTO> findUsuarioById(Long id);
    public Response<UsuarioDTO> saveUsuario(UsuarioDTO usuario);
    public Response<UsuarioDTO> updateUsuario(Long id, UsuarioDTO usuario);
    public Response<Boolean> disableOrEnableUsuario(Long id);
    public Response<Boolean> enableUsuario(Long id);
    public Response<UsuarioDTO> findUsuarioByEmail(String email);

}
