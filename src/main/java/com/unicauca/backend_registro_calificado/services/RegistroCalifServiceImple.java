package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ObservacionDTO;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.domain.UsuarioDTO;
import com.unicauca.backend_registro_calificado.model.Observacion;
import com.unicauca.backend_registro_calificado.model.ProgramaAcademico;
import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import com.unicauca.backend_registro_calificado.model.SubItem;
import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import com.unicauca.backend_registro_calificado.repository.IObservacionRepository;
import com.unicauca.backend_registro_calificado.repository.IProgramaAcademicoRepository;
import com.unicauca.backend_registro_calificado.repository.IRegistroCalifRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private final IProgramaAcademicoRepository iProgramaAcademicoRepository;

    public RegistroCalifServiceImple(ModelMapper modelMapper, IRegistroCalifRepository registroCalifRepository, IObservacionRepository observacionRepository, IProgramaAcademicoRepository iProgramaAcademicoRepository) {
        this.modelMapper = modelMapper;
        this.iRegistroCalifRepository = registroCalifRepository;
        this.iObservacionRepository = observacionRepository;
        this.iProgramaAcademicoRepository = iProgramaAcademicoRepository;
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
    /**
     * Este método permite actualizar el estado del documento cuando se aprueba o se rechaza
     * @param objObservacion es la observación realizada por el administrador
     * @param estado indica si es rechazado o aprobado
     * @return un mensaje indicando si la operación se realizó con éxito
     */
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
        Response<List<RegistroCalificadoDTO>> response = new Response<>();
        try{
            List<RegistroCalificado> lstRegistroCal = iRegistroCalifRepository.findByEstado(estado);
            response.setStatus(!lstRegistroCal.isEmpty() ? 200 : 404);
            response.setUserMessage(!lstRegistroCal.isEmpty() ? "Registros calificados encontrados con éxito" : "No se encuentran registros calificados asociados a este estado");
            response.setDeveloperMessage(!lstRegistroCal.isEmpty() ? "Registros calificados encontrados con éxito" : null);
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAllByEstado");
            response.setData(!lstRegistroCal.isEmpty() ? lstRegistroCal.stream().map(registroCalificado -> modelMapper.map(registroCalificado, RegistroCalificadoDTO.class)).collect(Collectors.toList()) : null);
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

    @Override
    public Response<List<RegistroCalificadoDTO>> findAll() {
        Response<List<RegistroCalificadoDTO>> response = new Response<>();
        try{
            List<RegistroCalificado> lstRegistroCal = this.iRegistroCalifRepository.findAll();
            response.setStatus(!lstRegistroCal.isEmpty() ? 200 : 404);
            response.setUserMessage(!lstRegistroCal.isEmpty() ? "Registros calificados encontrados con éxito" : "No se encuentran registros calificados ");
            response.setDeveloperMessage(!lstRegistroCal.isEmpty() ? "Registros calificados encontrados con éxito" : null);
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAll");
            response.setData(!lstRegistroCal.isEmpty() ? lstRegistroCal.stream().map(registroCalificado -> modelMapper.map(registroCalificado, RegistroCalificadoDTO.class)).collect(Collectors.toList()) : null);
            return response;
        } catch (Exception e){
            response.setStatus(500);
            response.setUserMessage("Error al encontrar los registros calificados. Causado por: "+e.getMessage());
            response.setDeveloperMessage("Error al encontrar los registros calificados. Causado por: "+e.getMessage());
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAll");
            response.setData(null);
            return response;
        }
    }

    @Override
    public Response<List<RegistroCalificadoDTO>> findAllByProgramaAcademico(Long programId) {
        Response<List<RegistroCalificadoDTO>> response = new Response<>();
        try{
            List<RegistroCalificado> lstRegistroCal = this.iRegistroCalifRepository.findAllByProgramaAcademicoId(programId);
            response.setStatus(!lstRegistroCal.isEmpty() ? 200 : 404);
            response.setUserMessage(!lstRegistroCal.isEmpty() ? "Registros calificados encontrados con éxito" : "No se encuentran registros calificados asociados a este programa academico");
            response.setDeveloperMessage(!lstRegistroCal.isEmpty() ? "Registros calificados encontrados con éxito" : null);
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAllByProgramaAcademico");
            response.setData(!lstRegistroCal.isEmpty() ? lstRegistroCal.stream().map(registroCalificado -> modelMapper.map(registroCalificado, RegistroCalificadoDTO.class)).collect(Collectors.toList()) : null);
            return response;
        } catch (Exception e){
            response.setStatus(500);
            response.setUserMessage("Error al encontrar los registros calificados. Causado por: "+e.getMessage());
            response.setDeveloperMessage("Error al encontrar los registros calificados. Causado por: "+e.getMessage());
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAllByProgramaAcademico");
            response.setData(null);
            return response;
        }
    }

    @Override
    public Response<RegistroCalificadoDTO> findRegistroCalificadoByAutor(String autor) {
        Response<RegistroCalificadoDTO> response = new Response<>();
        try{
            RegistroCalificado registroCalificado = this.iRegistroCalifRepository.findByAutor(autor);
            response.setStatus(registroCalificado != null  ? 200 : 404);
            response.setUserMessage(registroCalificado != null ? "Registros calificado encontrado con éxito" : "No se encuentraa un registro calificado asociado a este programa academico");
            response.setDeveloperMessage(registroCalificado != null ? "Registro calificado encontrado con éxito" : null);
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findRegistroCalificadoById");
            response.setData(registroCalificado != null ? modelMapper.map(registroCalificado, RegistroCalificadoDTO.class) : null);
            return response;
        } catch (Exception e){
            response.setStatus(500);
            response.setUserMessage("Error al encontrar el registro calificado. Causado por: "+e.getMessage());
            response.setDeveloperMessage("Error al encontrar los registros calificados. Causado por: "+e.getMessage());
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAllByProgramaAcademico");
            response.setData(null);
            return response;
        }
    }

}


