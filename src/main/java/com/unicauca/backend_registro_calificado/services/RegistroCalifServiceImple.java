package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ObservacionDTO;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.domain.UsuarioDTO;
import com.unicauca.backend_registro_calificado.model.Observacion;
import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import com.unicauca.backend_registro_calificado.repository.IObservacionRepository;
import com.unicauca.backend_registro_calificado.repository.IRegistroCalifRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
public class RegistroCalifServiceImple implements IRegistroCalificadoService{

    private static final Logger logger = LoggerFactory.getLogger(RegistroCalifServiceImple.class);
    private final ModelMapper modelMapper;
    private final IRegistroCalifRepository iRegistroCalifRepository;
    private final IObservacionRepository iObservacionRepository;

    public RegistroCalifServiceImple(ModelMapper modelMapper, IRegistroCalifRepository registroCalifRepository, IObservacionRepository observacionRepository) {
        this.modelMapper = modelMapper;
        this.iRegistroCalifRepository = registroCalifRepository;
        this.iObservacionRepository = observacionRepository;
    }

    @Override
    public Response<RegistroCalificadoDTO> createRegistroCalificado(RegistroCalificadoDTO registroCalificadoDTO) {

        logger.debug("Init createRegistroCalificado: {}", registroCalificadoDTO.toString());
        Response<RegistroCalificadoDTO> response = new Response<>();

        try{
            RegistroCalificado registroCalificado = modelMapper.map(registroCalificadoDTO, RegistroCalificado.class);
            RegistroCalificadoDTO registroCalificadoDTO1 = modelMapper.map(iRegistroCalifRepository.save(registroCalificado), RegistroCalificadoDTO.class);
            System.out.println("Miremos esto: " + registroCalificadoDTO1.toString());
            response.setStatus(200);
            response.setUserMessage("Registro Calificado creado exitosamente");
            response.setDeveloperMessage("Registro Calificado creado exitosamente");
            response.setMoreInfo("localhost:8080/api/registroCalificado");
            response.setErrorCode("");
            response.setData(registroCalificadoDTO1);
            logger.debug("Finish createRegistroCalificado Business");
            return response;
        }catch(Exception e){
            logger.error("Error createRegistroCalificado: {}", e.getMessage());
            response.setStatus(500);
            response.setUserMessage("Error al crear el registro calificado"+e.getMessage());
            response.setDeveloperMessage("Error al crear el registro calificado"+e.getMessage());
            response.setMoreInfo("localhost:8080/api/registroCalificado");
            response.setData(null);
            return response;
        }
    }


    @Override
    public ResponseEntity<?> updateStateRegistroCalificado(ObservacionDTO objObservacion, EstadoRegistroCal estado) {
        try{
            Observacion objObservacionEnt = modelMapper.map(objObservacion, Observacion.class);
            RegistroCalificado objR = objObservacionEnt.getRegistroCalificado();
            objR.setEstado(estado);
            iRegistroCalifRepository.save(objR);
            iObservacionRepository.save(objObservacionEnt);
            return new ResponseEntity("Operación realizada con exito", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("LA OPERACION NO SE HA PODIDO REALIZAR. Causado por: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Response<List<RegistroCalificadoDTO>> findAllByEstado(EstadoRegistroCal estado) {
        System.out.println("estado "+estado);
        List<RegistroCalificado> lstRegistroCal = iRegistroCalifRepository.findByEstado(estado);
        Response<List<RegistroCalificadoDTO>> response = new Response<>();
        try{
            List<RegistroCalificado> lstRegistroCal = iRegistroCalifRepository.findByEstado(estado);
            List<RegistroCalificadoDTO> registrosCalificadosDTO= lstRegistroCal.stream().map(registroCalificado ->  modelMapper.map(registroCalificado, RegistroCalificadoDTO.class)).collect(Collectors.toList());
            response.setStatus(200);
            response.setUserMessage("Registros calificados encontrados con éxito");
            response.setDeveloperMessage("Registros calificados encontrados con éxito");
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAllByEstado");
            response.setData(registrosCalificadosDTO);
            return response;
        }catch (Exception e){
            response.setStatus(500);
            response.setUserMessage("Error al encontrar los registros calificados. Causado por: "+e.getMessage());
            response.setDeveloperMessage("Error al encontrar los registros calificados. Causado por: "+e.getMessage());
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAllByEstado");
            response.setData(null);
            return response;
        }
    }

    @Override
    public Response<List<RegistroCalificadoDTO>> findAllByDate(String fechaInicio, String fechaFin) throws ParseException {
        System.out.println("fecha inicio: "+fechaInicio);
        System.out.println("fecha fin: "+fechaFin);

        Response<List<RegistroCalificadoDTO>> response = new Response<>();
        try{
            List<RegistroCalificado> lstRegistroCal = iRegistroCalifRepository.findAll();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date inicio = dateFormat.parse(fechaInicio);
            Date fin = dateFormat.parse(fechaFin);
            System.out.println("Inicio "+inicio.getTime());
            System.out.println("Fin "+fin.getTime());
            System.out.println("pruebaa 0"+lstRegistroCal.get(0).getFecha_creacion().getTime());
            System.out.println("pruebaa 1"+lstRegistroCal.get(1).getFecha_creacion().getTime());
            System.out.println("pruebaa 2"+lstRegistroCal.get(2).getFecha_creacion().getTime());

            lstRegistroCal=lstRegistroCal.stream()
                    .filter(registroCalificado -> registroCalificado.getFecha_creacion().equals(new Date(inicio.getTime())) ||
                            registroCalificado.getFecha_creacion().after(new Date(inicio.getTime())) &&
                                    registroCalificado.getFecha_creacion().before(new Date(fin.getTime())) ||
                            registroCalificado.getFecha_creacion().equals(new Date(fin.getTime())))
                    .collect(Collectors.toList());

            List<RegistroCalificadoDTO> lstRegistroDTO = lstRegistroCal.stream().map(registroCalificado -> modelMapper.map(registroCalificado, RegistroCalificadoDTO.class)).collect(Collectors.toList());
            response.setStatus(200);
            response.setUserMessage("Registros calificados encontrados con éxito");
            response.setDeveloperMessage("Registros calificados encontrados con éxito");
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAllByDate");
            response.setData(lstRegistroDTO);
            return response;
        }catch (Exception e){
            response.setStatus(500);
            response.setUserMessage("Error al encontrar los registros calificados. Causado por: "+e.getMessage());
            response.setDeveloperMessage("Error al encontrar los registros calificados. Causado por: "+e.getMessage());
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAllByDate");
            response.setData(null);
            return response;
        }
    }
}


