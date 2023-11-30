package com.unicauca.backend_registro_calificado.services;

import org.springframework.http.ResponseEntity;

public interface IDocumentoService {

    public ResponseEntity<byte[]> downloadWordFile(Integer IdRegistroCalificado) throws Exception;

    public ResponseEntity<byte[]> downloadPDFFile(Integer IdRegistroCalificado) throws Exception;

}
