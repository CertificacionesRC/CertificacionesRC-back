package com.unicauca.backend_registro_calificado.security.auth.exception;

public class UserDisabledException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDisabledException(String message) {
        super(message);
    }
}