package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ObservacionDTO;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface IRegistroCalificadoService {

    public Response<RegistroCalificadoDTO> createRegistroCalificado(RegistroCalificadoDTO registroCalificadoDTO);
    public ResponseEntity<?> updateStateRegistroCalificado(ObservacionDTO objObservacion, EstadoRegistroCal estado);

    public Response<List<RegistroCalificadoDTO>> findAllByEstado(EstadoRegistroCal estado);
    public Response<List<RegistroCalificadoDTO>> findAllByDate(String fechaInicio, String fechaFin) throws ParseException;

}
