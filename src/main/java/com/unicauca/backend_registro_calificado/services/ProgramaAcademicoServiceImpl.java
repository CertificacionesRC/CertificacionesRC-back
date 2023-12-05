package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ProgramaAcademicoDTO;
import com.unicauca.backend_registro_calificado.model.ProgramaAcademico;
import com.unicauca.backend_registro_calificado.repository.IProgramaAcademicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProgramaAcademicoServiceImpl implements IProgramaAcademicoService {

    private final IProgramaAcademicoRepository repository;
    ModelMapper modelMapper = new ModelMapper();
    public ProgramaAcademicoServiceImpl(IProgramaAcademicoRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<ProgramaAcademicoDTO> findAll() {

        List<ProgramaAcademico> programasAcademicos = repository.findAll();
        return  programasAcademicos.stream().map(programaAcademico -> modelMapper.map(programaAcademico, ProgramaAcademicoDTO.class)).collect(Collectors.toList());

    }
}
