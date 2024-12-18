package com.unicauca.backend_registro_calificado.security.auth.Filter;

import java.io.IOException;

import com.unicauca.backend_registro_calificado.model.Usuario;
import com.unicauca.backend_registro_calificado.security.auth.exception.UserDisabledException;
import com.unicauca.backend_registro_calificado.security.auth.service.JWTService;
import com.unicauca.backend_registro_calificado.security.auth.service.JWTServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//se extiende de esta clase porque vamos a usar un username y un password para realizar el login y acceder los recursos del API 
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	// verifica las credenciales del usuario utilizando JWT
	private AuthenticationManager authenticationManager;
	
	private JWTService jwtService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		this.authenticationManager = authenticationManager;
		// setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login",
		// "POST"));
		this.jwtService = jwtService;
	}

	// Este metodo se encarga de realizar la autenticación
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username != null && password != null) {
			// De esta forma podemos ver en consola cuales son los valores que recibimos en
			// el API rest para realizar login
			logger.info("Username desde request parameter (form-data): " + username);
			logger.info("Password desde request parameter (form-data): " + password);

		} else {
			Usuario user = null;
			try {

				user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

				username = user.getCorreo();
				password = user.getContrasena();
				// se debe tenr en cuenta que desde desde el json ya no se envia "username" sino "email"
				// porque es el campo con el que se esta trabajando
				logger.info("Username desde request InputStream (raw): " + username);
				logger.info("Password desde request InputStream (raw): " + password);

			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		username = username.trim();

		// Se encarga de contener las credenciales (usuario y contraseña)...Token que se maneja de forma interna
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		// Intenta autenticar al usuario utilizando el token de autenticación proporcionado
		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// Creamos el token que vamos a retornar al cliente
		String token = jwtService.create(authResult);
		
		// Guardamos el token en el parametro Authorization, e indicamos el prefijo Bearer
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); // Reemplaza con el origen de tu aplicación React
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "*");
		response.setHeader("Access-Control-Max-Age", "3600");

		response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		body.put("user", (org.springframework.security.core.userdetails.User) authResult.getPrincipal());
		body.put("mensaje", String.format("Hola %s, has iniciado sesión con éxito",
				((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername()));
		System.out.println();
		// ObjectMapper: convertir el objeto map a un objeto de tipo json
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		Map<String, Object> body = new HashMap<>();
		Throwable cause = failed.getCause();
		// Verifica si la causa de la excepción es una instancia de la clase UserDisabledException
		if (cause instanceof UserDisabledException) {
			// Excepción específica para usuario deshabilitado
			body.put("mensaje", "El usuario está deshabilitado");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		} else {
			// Manejo genérico para otras excepciones
			body.put("mensaje", "Error de autenticación: username o password incorrecto");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
		// ObjectMapper: convertir el objeto map a un objeto de tipo json
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setContentType("application/json");
	}

}
