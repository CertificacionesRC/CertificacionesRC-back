package com.unicauca.backend_registro_calificado.security.auth.service;

import java.util.ArrayList;
import java.util.List;

import com.unicauca.backend_registro_calificado.model.Rol;
import com.unicauca.backend_registro_calificado.model.Usuario;
import com.unicauca.backend_registro_calificado.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("JpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByCorreo(correo);
        
        if(usuario == null) {
        	logger.error("Error en el Login: no existe el usuario registrado con el correo'" + correo + "' en el sistema!");
        	throw new UsernameNotFoundException("Correo: " + correo + " no existe en el sistema!");
        }
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		// Agregar el rol con el que se autentico el usuario ya sea admin o user
		Rol rol = usuario.getRol(); // Obtén el primer (y único) rol del usuario
		logger.info("Role: ".concat(rol.getNombre()));
		authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
		
		 if(authorities.isEmpty()) {
	        	logger.error("Error en el Login: Usuario '" + correo + "' no tiene roles asignados!");
	        	throw new UsernameNotFoundException("Error en el Login: usuario '" + correo + "' no tiene roles asignados!");
	        }
		//Devolvemos un objeto user detail con la información necesaria para el inicio de sesión
		return new org.springframework.security.core.userdetails.User(usuario.getCorreo(), usuario.getContrasena(),true, true, true, true, authorities);
	}

}
