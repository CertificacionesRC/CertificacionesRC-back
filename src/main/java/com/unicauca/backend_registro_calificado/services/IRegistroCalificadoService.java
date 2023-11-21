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
    /**
     * Este método permite actualizar el estado del documento cuando se aprueba o se rechaza
     * @param objObservacion es la observación realizada por el administrador
     * @param estado indica si es rechazado o aprobado
     * @return un mensaje indicando si la operación se realizó con éxito
     */
    public ResponseEntity<?> updateStateRegistroCalificado(ObservacionDTO objObservacion, EstadoRegistroCal estado);

    public Response<List<RegistroCalificadoDTO>> findAllByEstado(EstadoRegistroCal estado);
    public Response<List<RegistroCalificadoDTO>> findAllByDate(String fechaInicio, String fechaFin) throws ParseException;
    public Response<List<RegistroCalificadoDTO>> findAll();
    public Response<List<RegistroCalificadoDTO>> findAllByProgramaAcademico(Long programaId);
}
