package com.unicauca.backend_registro_calificado.services;

import org.springframework.http.ResponseEntity;

public interface IPdfService {

    public ResponseEntity<byte[]> downloadPDFFile(Integer IdRegistroCalificado) throws Exception;

}
