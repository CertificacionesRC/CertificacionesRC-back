package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import com.unicauca.backend_registro_calificado.repository.IRegistroCalifRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegistroCalifServiceImple implements IRegistroCalificadoService{

    private static final Logger logger = LoggerFactory.getLogger(RegistroCalifServiceImple.class);
    private final ModelMapper modelMapper;
    private final IRegistroCalifRepository iRegistroCalifRepository;

    public RegistroCalifServiceImple(ModelMapper modelMapper, IRegistroCalifRepository registroCalifRepository) {
        this.modelMapper = modelMapper;
        this.iRegistroCalifRepository = registroCalifRepository;
    }


    @Override
    public Response<RegistroCalificadoDTO> createRegistroCalificado(RegistroCalificadoDTO registroCalificadoDTO) {
        logger.debug("Init createRegistroCalificado: {}", registroCalificadoDTO.toString());
        Response<RegistroCalificadoDTO> response = new Response<>();
        RegistroCalificado registroCalificado = modelMapper.map(registroCalificadoDTO, RegistroCalificado.class);
        RegistroCalificadoDTO registroCalificadoDTO1 = modelMapper.map(iRegistroCalifRepository.save(registroCalificado), RegistroCalificadoDTO.class);
        response.setStatus(200);
        response.setUserMessage("Registro Calificado creado exitosamente");
        response.setDeveloperMessage("Registro Calificado creado exitosamente");
        response.setMoreInfo("localhost:8080/api/registroCalificado");
        response.setErrorCode("");
        response.setData(registroCalificadoDTO1);
        logger.debug("Finish createRegistroCalificado Business");

        return response;
    }
}


