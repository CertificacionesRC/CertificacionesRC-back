package com.unicauca.backend_registro_calificado.util.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class MapperBean {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}